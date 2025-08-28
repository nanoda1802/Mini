package functions.project;

import configs.message.Ingredient;
import configs.message.SystemMessage;
import configs.message.UIMessage;
import configs.validation.RegEx;
import controller.controllers.ProjectController;
import managers.MessageBuilderManager;
import managers.ValidatorManager;
import managers.messageBuild.MessageBuilder;
import managers.messageBuild.SystemMessageBuilder;
import managers.messageBuild.UIMessageBuilder;
import managers.messageBuild.ingredient.TaskListMessageBuilder;
import model.project.Project;
import model.project.Task;
import utils.LogRecorder;
import utils.Pair;
import utils.console.InputReader;
import utils.console.Viewer;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// [ ProjectFuncs 개요 ]
// - "@@ 화면" 단계, @@은 각 기능의 한국 이름  ex) 업무등록 화면, 업무조회 화면, ...
// - UI : 기능 소개 + 입력양식 설명
// - System : (검증실패사유) + 정보입력 안내 + 홈화면 복귀 안내

// [ 메모 ]
// - 코드 전반에 활용된 Pair 클래스에 대한 설명은 utils.Pair.java 파일에 있습니다!
// - "업무등록" 과 "업무정보수정" 에서 담당자 정보가 변경되거나, Task가 제거될 경우 해당 Member 인스턴스의 tasks 필드도 최신화해주기!
// - "업무정보수정" 에서 수정될 Task 인스턴스의 updatedAt 갱신도 잊지 않기! (자동으로 갱신되는 Util 메서드를 만드...?)
// - 486을 입력해 홈 화면으로 복귀하는 기능은 구현되지 않았습니다!!


public class ProjectFuncs {
    /* [ "업무등록" 선택 시 실행될 메서드 ] */
    public static void addTask() {
        Pair<Boolean, String> alert = new Pair<>(true, "");  // [메모] System 메세지 갱신에 활용할 지역변수
        // [1] 업무등록 화면 유지할 반복문 시작
        while (true) {
            // [Loop-1] UI와 System 문자열 제작해 출력
            Viewer.clear();

            UIMessageBuilder uiBuilder = MessageBuilderManager.ui;
            SystemMessageBuilder sysBuilder = MessageBuilderManager.system;

            String uiMsg = uiBuilder.build(UIMessage.ADD_TASK.getMsg());
            String sysMsg = alert.getKey() // [메모] alert의 key에는 유효성 검사 결과 bool 값이 담김, 이를 기준으로 다른 분기의 System 메세지 출력
                    ? sysBuilder.build(SystemMessage.ADD_TASK.getMsg())
                    : sysBuilder.build(SystemMessage.ADD_TASK_FAILED.getMsg(), MessageBuilder.pack(alert.getValue()));

            Viewer.print(MessageBuilder.integrate(uiMsg, sysMsg));

            // [Loop-2] 사용자의 입력
            String input = InputReader.read();

            // [Loop-2-A] 특정 번호 입력 시 홈 화면으로 복귀
            if (input.equals("486")) {
                return;
            }
            // [Loop-3] 입력값에 대한 유효성 검사
            alert = ValidatorManager.addTask.check(input);

            // [Loop-4] 검증 성공 시 입력된 정보를 컨트롤러에 전달, 업무 생성 로그 기록
            if (alert.getKey()) {
                String[] inputs = alert.getValue().split("/");
                Task newTask = Project.getInstance().controller.add(inputs);

                String taskName = newTask.getName();
                LogRecorder.record(Ingredient.LOG_ADD_TASK,taskName);
                // [메모] 시스템 메세지 갱신을 위해선 alert의 key가 false가 돼줘야 함...
                alert = new Pair<>(false,String.format(Ingredient.ADD_TASK_SUCCESS.getFormat(),taskName,newTask.getTid()));
            }
        }

    }

    /* [ "업무정보수정" 선택 시 실행될 메서드 ] */
    public static void updateTaskInfo() {
        Pair<Boolean, String> alert = new Pair<>(true, "");  // [메모] System 메세지 갱신에 활용할 지역변수
        // [1] 업무등록 화면 유지할 반복문 시작
        while (true) {
            // [Loop-1] UI와 System 문자열 제작해 출력
            Viewer.clear();

            UIMessageBuilder uiBuilder = MessageBuilderManager.ui;
            SystemMessageBuilder sysBuilder = MessageBuilderManager.system;

            String uiMsg = uiBuilder.build(UIMessage.UPDATE_TASK_INFO.getMsg());
            String sysMsg = alert.getKey() // [메모] alert의 key에는 유효성 검사 결과 bool 값이 담김, 이를 기준으로 다른 분기의 System 메세지 출력
                    ? sysBuilder.build(SystemMessage.UPDATE_TASK_INFO.getMsg())
                    : sysBuilder.build(SystemMessage.UPDATE_TASK_INFO_FAILED.getMsg(), MessageBuilder.pack(alert.getValue()));

            Viewer.print(MessageBuilder.integrate(uiMsg, sysMsg));

            // [Loop-2] 사용자의 입력
            String input = InputReader.read();

            // [Loop-2-A] 특정 번호 입력 시 홈 화면으로 복귀
            if (input.equals("486")) {
                return;
            }

            ProjectController pc = Project.getInstance().controller;

            // [Loop-3] TID만 입력됐을 시 해당 업무 제거 시도
            if (Pattern.matches(RegEx.UPDATE_TASK_INFO_TID.getPattern(),input)) {
                Task targetTask = pc.get(input);
                if (targetTask == null) {
                    // [3 A] 제거할 업무 찾지 못했다는 메세지 전달
                    alert = new Pair<>(false,Ingredient.REMOVE_TASK_FAILED.getFormat());
                    continue;
                } else {
                    // [3 B] 제거에 성공했다는 메세지 전달 및 로그 기록
                    String taskName = targetTask.getName();
                    alert = new Pair<>(false,String.format(Ingredient.REMOVE_TASK_SUCCESS.getFormat(),taskName));
                    LogRecorder.record(Ingredient.LOG_REMOVE_TASK,taskName);
                    pc.remove(targetTask.getTid()); // [메모] 로그 저장을 먼저하지 않으면 에러 발생
                    continue;
                }
            }

            // [Loop-4] 입력값에 대한 유효성 검사
            alert = ValidatorManager.updateTaskInfo.check(input);

            // [Loop-5] 검증 성공 시 입력된 정보로 업무 수정 및 로그 기록
            if (alert.getKey()) {
                String[] inputs = alert.getValue().split("/");
                pc.update(alert.getValue().split("/"));

                // [메모] 업무명이 입력되지 않았을 수 있으니 찾아서 넣어줘야 함
                String taskName = inputs[1].equals("@") ? pc.get(inputs[0]).getName() : inputs[1];
                LogRecorder.record(Ingredient.LOG_UPDATE_TASK_INFO,taskName);

                // [메모] 시스템 메세지 갱신을 위해선 alert의 key가 false가 돼줘야 함...
                alert = new Pair<>(false,String.format(Ingredient.UPDATE_TASK_INFO_SUCCESS.getFormat(),taskName));
            }
        }
    }

    /* [ "업무조회" 선택 시 실행될 메서드 ] */
    public static void browseTasks() {
        Pair<Boolean, String> alert = new Pair<>(true, "");  // [메모] System 메세지 갱신에 활용할 지역변수
        // [1] 업무조회 화면 유지할 반복문 시작
        while (true) {
            // [Loop-1] UI와 System 문자열 제작해 출력
            Viewer.clear();

            UIMessageBuilder uiBuilder = MessageBuilderManager.ui;
            SystemMessageBuilder sysBuilder = MessageBuilderManager.system;

            String uiMsg = uiBuilder.build(UIMessage.BROWSE_TASKS.getMsg());
            String sysMsg = alert.getKey() // [메모] alert의 key에는 유효성 검사 결과 bool 값이 담김, 이를 기준으로 다른 분기의 System 메세지 출력
                    ? sysBuilder.build(SystemMessage.BROWSE_TASKS.getMsg())
                    : sysBuilder.build(SystemMessage.BROWSE_TASKS_FAILED.getMsg(), MessageBuilder.pack(alert.getValue()));

            Viewer.print(MessageBuilder.integrate(uiMsg, sysMsg));

            // [Loop-2] 사용자의 입력
            String input = InputReader.read();

            // [Loop-2-A] 특정 번호 입력 시 홈 화면으로 복귀
            if (input.equals("486")) {
                return;
            }

            // [Loop-3] 입력값에 대한 유효성 검사
            alert = ValidatorManager.browseTasks.check(input);

            // [Loop-4] 검사 통과했다면 조건에 해당하는 업무 목록 화면으로 이동
            if (alert.getKey()) {
                if(showFilteredTasks(alert.getValue().split("/"))) {
                    return; // [메모] 486 입력됐다면 true를 반환해 홈화면으로 복귀함
                }
            }
        }
    }

    /* "업무조회" 파생 화면 -> 업무 목록 출력 */
    public static boolean showFilteredTasks(String[] inputs) {
        // [1] Project에서 조건에 해당하는 Task들의 정보 추출
        Stream<Task> browsing = Project.getInstance().controller.browse(inputs);
        List<String> filteredTasks = browsing.map(Task::toString).toList();

        // [2] 필터링된 정보들로 재료 메세지 제작
        TaskListMessageBuilder taskListBuilder = MessageBuilderManager.taskList;
        String messageIngredients = taskListBuilder.build(Ingredient.TASK_LIST.getFormat(), filteredTasks);

        // [3] 재료 메세지들로 최종 메세지 제작
        UIMessageBuilder uiBuilder = MessageBuilderManager.ui;
        SystemMessageBuilder sysBuilder = MessageBuilderManager.system;

        String uiMsg = uiBuilder.build(UIMessage.BROWSE_TASKS_RESPOND.getMsg(), MessageBuilder.pack(messageIngredients));
        String sysMsg = sysBuilder.build(SystemMessage.BROWSE_TASKS_RESPOND.getMsg());

        // [4] 갈무리한 업무 목록 출력
        Viewer.clear();
        Viewer.print(MessageBuilder.integrate(uiMsg, sysMsg));

        // [5] 특정 번호 입력 시 홈 화면으로 복귀, 아니라면 다시 조회화면으로
        String input = InputReader.read();
        return input.equals("486");
    }
}


