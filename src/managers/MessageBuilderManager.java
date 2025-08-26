package managers;

import managers.messageBuild.MessageBuilder;
import managers.messageBuild.SystemMessageBuilder;
import managers.messageBuild.UIMessageBuilder;
import managers.messageBuild.ingredient.OverviewMessageBuilder;
import managers.messageBuild.ingredient.TaskListMessageBuilder;

// [ MessageBuilderManager 클래스 설명 ]
// - MessageBuilderManager는 MessageBuilder 클래스들을 일괄 관리하고 전역에서 호출하기 위한 클래스임다.
// - 차후 프로그램 Init 과정이 생긴다면, 필드들을 순회하며 null 체크를 해보는 등 안정성을 높일 수 있습니다.

// [ 메모 ]
// - 유형별 MessageBuilder 클래스 추가될 때마다 필드 추가 (AMessageBuilder인 경우 필드명을 A로 작명)
// - 필드 타입은 자식 MessageBuilder로 지정 (직접 활용해보니 매번 다운캐스팅을 해야 메서드를 실행할 수 있어서 번거로웠음)
// - build 과정은 먼저 재료 Message들을 제작하고, UI 및 System Message를 제작하는 절차로 진행됩니다.

public class MessageBuilderManager extends Manager<MessageBuilder> {
    // [ 최종 MessageBuilder ]
    public static UIMessageBuilder ui = new UIMessageBuilder();
    public static SystemMessageBuilder system = new SystemMessageBuilder();
    // [ 재료 MessageBuilder ]
    public static OverviewMessageBuilder overview = new OverviewMessageBuilder();
    public static TaskListMessageBuilder taskList = new TaskListMessageBuilder();
}