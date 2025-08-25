package functions.project;

import configs.message.SystemMessage;
import configs.message.UIMessage;
import managers.MessageBuilderManager;
import managers.ValidatorManager;
import managers.messageBuild.SystemMessageBuilder;
import managers.messageBuild.UIMessageBuilder;
import model.project.Project;
import utils.Pair;
import utils.console.InputReader;
import utils.console.Viewer;

import java.util.List;

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
                    : sysBuilder.build(new Pair<String, List<Object>>(SystemMessage.ADD_TASK_FAILED.getMsg(), sysBuilder.pack(alert.getValue())));

            Viewer.print(sysBuilder.integrate(uiMsg, sysMsg));

            // [Loop-2] 사용자의 입력에 대한 유효성 검사
            String input = InputReader.read();
            Pair<Boolean, String> checkResult = ValidatorManager.addTask.check(input);

            // [Loop-2-A] 검사 결과가 true가 아니면 재입력 위해 continue
            if (!checkResult.getKey()) {
                alert = checkResult; // [메모] checkResult를 통해 alert에 { false, 실패 사유 } 전달
                continue;
            }

            // [Loop-3] 컨트롤러 호출해 검증된 입력값을 Add (split 해서)
            Project.getInstance().controller.add(checkResult.getValue().split("/"));

            // [Loop-3-A 추가예정] 만약 담당자 항목이 입력됐다면, 해당 Member 인스턴스의 tasks에도 Add

            // [Loop-End] 홈 화면으로 복귀하기 위한 return
            return;
        }

    }

    /* [ "업무정보수정" 선택 시 실행될 메서드 ] */
    public static void updateTaskInfo() {
        Pair<Boolean, String> alert = new Pair<>(true, "");  // [메모] System 메세지 갱신에 활용할 지역변수
        // [1] 업무등록 화면 유지할 반복문 시작
        // [Loop-1] UI와 System 문자열 제작해 출력
        // [Loop-2] 사용자의 입력에 대한 유효성 검사
        // [Loop-2-A] 검사 결과가 true가 아니면 재입력 위해 continue
        // [Loop-3] 컨트롤러 호출해 검증된 입력값을 update (split 해서)
        // [Loop-3-A 추가예정] 만약 담당자 항목이 수정됐다면, 해당 Member 인스턴스의 tasks에도 Add
        // [Loop-End] 홈 화면으로 복귀하기 위한 return
    }

    /* [ "업무조회" 선택 시 실행될 메서드 ] */
    public static void browseTasks() {
    }
}


