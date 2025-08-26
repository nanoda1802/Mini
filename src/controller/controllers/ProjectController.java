package controller.controllers;

import configs.project.TaskType;
import controller.*;
import managers.ConverterManager;
import model.project.Project;
import model.project.Task;
import model.team.Member;
import model.team.Team;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

// [ ProjectController 클래스 설명 ]
// - ProjectController는 Task 인스턴스들에 대한 CRUD 조작을 처리하기 위한 Controller 기반의 클래스임다.
// - Project 클래스를 통해서만 인스턴스를 생성하고, Project의 HashMap 필드인 tasks를 참조하는 종속성을 갖슴다.

// [ 메모 ]
// - add() 메서드 실행 마다 TID를 생성하고, index 필드의 값을 1씩 implement 해야 합니다.
// - Task 인스턴스 생성 시 입력값이 보류된 필드에 대한 처리 방법의 고민이 필요합니다.
//      ex) "비품구매/4/@/20270722" -> 보류된 assignee 필드에 null을 할당...? Optional.empty()...?
// - "업무조회" 기능 관련해, 보류된 필드에 대한 출력값 설정이 필요합니다.
//      ex) name=비품구매/type=기타/status=진행/assignee=null" -> 콘솔화면에서 assignee는 "미정"으로...?

public class ProjectController extends Controller implements Adder, Getter<Task>, Updater, Remover {
    private Map<String, Task> tasks;
    private long index = 1;

    public ProjectController(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    /* Create 담당 */
    @Override
    public void add(String[] infos) {
        // infos = 업무명 / 유형 / 담당자ID / 마감일
        // 자료형 = String / TaskType / Member / LocalDate

        // [1] 항목별로 Task의 각 필드타입에 맞게 convert
        String tid = createId();
        String name = infos[0];
        TaskType type = ConverterManager.stringTaskType.convertTo(infos[1]);
        Member assignee = infos[2].equals("@")
                ? null
                : Team.getInstance().controller.get(infos[2]);
                // [추가예정] Team.members에서 담당자 인스턴스 찾지 못 했을 경우 구현해야 함
        LocalDate dueTo = infos[3].equals("@")
                ? null
                : ConverterManager.stringDate.convertTo(infos[3]);

        // [2] 신규 Task 인스턴스 생성
        Task newTask = new Task(tid,name,type,assignee,dueTo);

        // [3] tasks에 새 Task 인스턴스 생성해 추가
        tasks.put(tid,newTask);

        // [3-A] 만약 담당자 항목이 입력됐다면, 해당 Member 인스턴스의 tasks에도 Add
        if (assignee != null) {
            assignee.addTask(newTask);
        }
    }

    /* Update 담당 */
    @Override
    public void update(String[] changes) {
        // changes = TID / 업무명 / 상태 / 담당자ID / 마감일
        // 자료형 = String / String / TaskStatus / Member / LocalDate

        // [1] TID로 해당 Task 인스턴스 찾아오기
        Task targetTask = get(changes[0]);
        // [2] 입력값 바탕으로 각 필드 수정
        if (!changes[1].equals("@")) { // [A] 업무명 수정
            targetTask.setName(changes[1]);
        }
        if (!changes[2].equals("@")) { // [B] 업무상태 수정
            targetTask.setStatus(ConverterManager.stringTaskStatus.convertTo(changes[2]));
        }
        if (!changes[3].equals("@")) { // [C] 담당자 수정
            // [C-1] 기존 담당자가 있는지 체크, 있다면 수정된 업무 그의 tasks에서 제거
            Member prevAssignee = targetTask.getAssignee();
            if (prevAssignee != null) {
                prevAssignee.removeTask(targetTask);
            }

            // [C-2] 다음 담당자 찾아서 그의 tasks에 추가
            // [추가예정] mid로 담당자 찾지 못했을 경우 구현해야 함
            Member nextAssignee = Team.getInstance().controller.get(changes[3]);
            if (nextAssignee != null) {
                nextAssignee.addTask(targetTask);
            }

            // [C-3] 지금 업무의 담당자 수정
            targetTask.setAssignee(nextAssignee);
        }
        if (!changes[4].equals("@")) { // [D] 마감일 수정
            targetTask.setDueTo(ConverterManager.stringDate.convertTo(changes[4]));
        }
        // [3] 해당 Task의 updatedAt 필드 최신화
        targetTask.updateTime();
    }

    /* Read 담당 */
    @Override
    public Task get(String tid) {
        return tasks.get(tid);
    }

    /* Delete 담당 */
    @Override
    public void remove(String tid) {
        tasks.remove(tid);
    }

    /* 조건에 부합하는 Task의 정보를 추출하는 메서드 */
    public Stream<Task> browse(String[] inputs) {
        // [1] tasks에 대한 스트림 시작
        Stream<Task> filtering = this.getAll().stream();

        // [2] 입력받은 기준들을 순회하며 필터링 반복
        for (String input : inputs) {
            // [Loop-1] 입력값을 통해 조회기준과 조건을 추출
            String[] field = input.split(",");
            String criteria = field[0];
            String condition = field[1];

            // [Loop-2] 기준별로 조건에 부합하지 않는 Task 걸러내기
            switch (criteria) {
                case "1": // 업무유형 비교
                    filtering = filtering.filter(a -> a.getType() == ConverterManager.stringTaskType.convertTo(condition));
                    break;
                case "2": // 업무상태 비교
                    filtering = filtering.filter(a -> a.getStatus() == ConverterManager.stringTaskStatus.convertTo(condition));
                    break;
                case "3": // 담당자 비교
                    filtering = filtering.filter(a -> {
                        Member assignee = a.getAssignee(); // [메모] null 체크를 하지 않으면 무조건 에러 발생
                        return assignee != null && assignee.getMid().equals(condition);
                    });
                    break;
                default:
                    // [메모] 여기로 올 일이 없어서 우째야 하나 고민
                    // throw new Exception();
            }
        }

        // [3] 필터링 마친 Task들을 스트림 형태로 반환
        return filtering;
    }

    public Collection<Task> getAll() {
        return this.tasks.values();
    }

    private String createId() {
        return index < 10 ? "t0" + index++ : "t" + index++;
    }
}
