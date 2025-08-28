package controller.controllers;

import configs.team.Authority;
import controller.*;
import managers.ConverterManager;
import model.project.Project;
import model.project.Task;
import model.team.Member;
import utils.Pair;

import java.util.*;
import java.util.stream.Stream;

// [ TeamController 클래스 설명 ]
// - TeamController는 Member 인스턴스들에 대한 CRUD 조작을 처리하기 위한 Controller 기반의 클래스임다.
// - Team 클래스를 통해서만 인스턴스를 생성하고, Team의 HashMap 필드인 members를 참조하는 종속성을 갖슴다.

// [ 메모 ]
// - add() 메서드 실행 마다 MID를 생성하고, index 필드의 값을 1씩 implement 해야 합니다.
// - "팀원조회" 기능 관련해, 보류된 필드에 대한 출력값 설정이 필요합니다.
//      ex) name=홍길동/auth=null/tasks=null" -> 콘솔화면에서 auth는 "미정", tasks는 "없음"으로...?

public class TeamController extends Controller implements Adder, Getter<Member>, Updater, Remover {
    private Map<String, Member> members;
    private long index = 1;

    public TeamController(Map<String, Member> members) {
        this.members = members;
    }

    @Override
    public Member add(String[] infos) {
        // infos = 팀원명 / 권한
        // 자료형 = String / Authority

        // [1] 항목별로 Team의 각 필드타입에 맞게 convert
        String mid = createMID();
        String name = infos[0];
        Authority auth = ConverterManager.stringAuthority.convertTo(infos[1]);

        // [2] 멤버 신규 인스턴스 생성
        Member member = new Member(mid, name, auth);

        // [3] members에 멤버 저장
        members.put(mid, member);

        return  member;
    }

    @Override
    public Member get(String eid) {
        return members.get(eid);
    }

    @Override
    public void update(String[] changes) {
        // infos = 팀원ID / 팀원명 / 권한 / 담당업무(tid)
        // 자료형 = String / String / Authority / String
        // [1] 항목별로 Team의 각 필드타입에 맞게 convert
        String mid = changes[0];
        Member member = get(mid);
        if(member == null) {return;}
        String name = changes[1];
        Authority auth = changes[2].equals("@") ?
                member.getAuth() : ConverterManager.stringAuthority.convertTo(changes[2]);

        // [2] tid로 해당 팀원에게 업무 할당
        if(!changes[3].equals("@")){
            String[] tids = changes[3].split(",");
            for (String tid : tids) {
                Task task = Project.getInstance().controller.get(tid);
                if(task != null) {
                    member.addTask(task);

                    // task에 이미 담당자가 있다면 담당자를 변경한다.
                    Member oldAssignee = task.getAssignee();
                    if(oldAssignee == null) {
                        task.setAssignee(member);
                    }else {
                        changeAssignee(task, oldAssignee, member);
                    }
                }
            }
        }
        // [3] 다른 요소 업데이트
        if(!changes[1].equals("@")){
            member.setName(name);
        }
        member.setAuth(auth);

    }

    @Override
    public void remove(String mid) {

        if(members.containsKey(mid)) {
            if(members.get(mid).getTasks() != null) {
                // 담당자로 배정된 task에서 해임한다.
                Set<Task> tasks = members.get(mid).getTasks();
                for(Task task : tasks) {
                    task.setAssignee(null);
                }
            }
            members.remove(mid);
        }

    }

    private String createMID() {
        return index < 10 ? "m0" + index++ : "m" + index++;
    }

    public Collection<Member> getAll() {
        return this.members.values();
    }

    // task의 담당자를 바꾸는 메소드 (task, 이전 담당자, 바꿀 담당자)
    private void changeAssignee(Task task,Member oldAssignee,Member member){
        oldAssignee.removeTask(task);
        task.setAssignee(member);
    }
    /* 담당업무 보유 여부별 팀원 세기 (홈화면 overview에 활용) */
    public Pair<Integer, Integer> countAssignment() {
        // [1] 담당 업무가 있는 팀원 세기
        int assigneeCount = (int) this.getAll().stream().filter(member -> {
            Set<Task> tasks = member.getTasks();
            return tasks != null && !tasks.isEmpty();
        }).count(); // [변경예정] Math.toIntExact() 이 방법으로 형변환하기
        // [2] Pair 반환 (업무보유자 수, 전체 팀원 수)
        return new Pair<>(assigneeCount,members.size());
    }
    /* 조건에 부합하는 Member의 정보를 추출하는 메서드 */
    public Stream<Member> browse(String[] inputs) {
        Stream<Member> filtering = this.getAll().stream();
        if(inputs[0].equals("@")){
            return filtering;
        }

        List<String> filters = Arrays.asList(inputs);

        return filtering.filter(m ->{
            // 해당 항목을 고른지 안 고른지 우선 판단
            // 그 조건문 안에서 검사
            boolean found = true;
            if (filters.contains("1")||filters.contains("2")||filters.contains("3")) {
                found = (filters.contains("1") && m.getAuth() == Authority.ADMIN)||
                        (filters.contains("2") && m.getAuth() == Authority.MEMBER)||
                        (filters.contains("3") && m.getAuth() == Authority.VIEWER);
            }

            boolean hasTasks = !m.getTasks().isEmpty();
            boolean found2 = true;
            if (filters.contains("4")||filters.contains("5")) {
                found2 = (filters.contains("4") && hasTasks)||
                        (filters.contains("5") &&  !hasTasks );
            }
            return found && found2;
        });


    }
}
