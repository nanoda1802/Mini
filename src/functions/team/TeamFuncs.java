package functions.team;

// [ TeamFuncs 개요 ]
// - "@@ 화면" 단계, @@은 각 기능의 한국 이름  ex) 업무등록 화면, 업무조회 화면, ...
// - UI : 기능 소개 + 입력양식 설명
// - System : (검증실패사유) + 정보입력 안내 + 홈화면 복귀 안내

// [ 메모 ]
// - "팀원정보수정" 에서 tasks 정보가 변경되거나, 멤버가 해임될 경우 관련 Task 인스턴스들의 assignee 필드도 최신화해주기!
//    이 때, Task 인스턴스의 updatedAt에 대한 갱신도 잊지 않기! (자동으로 갱신되는 Util 메서드를 만드...?)
// - 486을 입력해 홈 화면으로 복귀하는 기능은 구현되지 않았습니다!!

public class TeamFuncs {
    /* [ "팀원초대" 선택 시 실행될 메서드 ] */
    public static void inviteMember() {
    }

    /* [ "팀원정보수정" 선택 시 실행될 메서드 ] */
    public static void updateMemberInfo() {
    }

    /* [ "팀원조회" 선택 시 실행될 메서드 ] */
    public static void browseMembers() {
    }

    // [보류] public static void setLeader() {}
}
