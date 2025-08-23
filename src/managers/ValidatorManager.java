package managers;

import managers.validation.AddTaskValidator;
import managers.validation.Validator;

// [ ValidatorManager 클래스 설명 ]
// - ValidatorManager는 Validator류 클래스들을 일괄 관리하고 전역에서 호출하기 위한 클래스임다.
// - 차후 프로그램 Init 과정이 생긴다면, 필드들을 순회하며 null 체크를 해보는 등 안정성을 높일 수 있습니다.

// [ 메모 ]
// - 기능별 Validator 클래스 추가될 때마다 필드 추가 (AValidator인 경우 필드명을 A로 작명)
// - 필드 타입은 자식 Validator로 지정 (직접 활용해보니 매번 다운캐스팅을 해야 메서드를 실행할 수 있어서 번거로웠음)

public class ValidatorManager extends Manager<Validator> {
    public static AddTaskValidator addTask = new AddTaskValidator();
//  [추가 예정] public static UpdateTaskInfoValidator updateTaskInfo = new UpdateTaskInfoValidator();
//  [추가 예정] public static BrowseTasksValidator browseTasks = new BrowseTasksValidator();
//  [추가 예정] public static InviteMemberValidator inviteMember = new InviteMemberValidator();
//  [추가 예정] public static UpdateMemberInfoValidator updateMemberInfo = new UpdateMemberInfoValidator();
//  [추가 예정] public static BrowseMembersValidator browseMembers = new BrowseMembersValidator();
}
