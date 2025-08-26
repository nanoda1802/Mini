package controller.controllers;

import configs.team.Authority;
import controller.*;
import managers.ConverterManager;
import model.project.Task;
import model.team.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
    public void add(String[] infos) {
        // infos = 팀원명 / 권한
        // 자료형 = String / Authority

        // [1] 항목별로 Team의 각 필드타입에 맞게 convert
        String mid =  createMID();
        String name = infos[0];
        Authority auth = ConverterManager.stringAuthority.convertTo(infos[1]);
        
        // [2] 멤버 신규 인스턴스 생성
        Member member = new Member(mid, name, auth);
        
        // [3] members에 멤버 저장
        members.put(mid, member);
    }

    @Override
    public Member get(String eid) {
        return null;
    }

    @Override
    public void update(String[] changes) {
        // infos = 팀원ID / 팀원명 / 권한 / 담당업무(tid)
        // 자료형 = String / String / Authority / String
        // [1] 항목별로 Team의 각 필드타입에 맞게 convert
        String name = changes[1];
        Authority auth = ConverterManager.stringAuthority.convertTo(changes[2]);
        String[] tids = changes[3].split(",");
        ArrayList<Task> tasks = new ArrayList<>();
        for (String tid : tids) {
        }
        // [2] mid로 해당 팀원 가져오기
        // [3] 멤버 업데이트
    }

    @Override
    public void remove(String eid) {
    }
    private String createMID(){
        return  index < 10 ? "m0" + index++ : "m" + index++;
    }

    public Collection<Member> getAll() {
        return this.members.values();
    }
}
