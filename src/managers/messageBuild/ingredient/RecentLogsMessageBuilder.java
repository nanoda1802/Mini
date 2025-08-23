package managers.messageBuild.ingredient;

import managers.messageBuild.MessageBuilder;
import utils.Pair;

import java.util.List;

// [ RecentLogsMessageBuilder 클래스 설명 ]
// - RecentLogsMessageBuilder 홈 화면에 활용되는 "최근 활동" 항목 제작을 위한 클래스입니다.

// [ 메모 ]
// - Build의 중간 단계에 해당하는 클래스입니다.
// - 제작된 RecentLogs는 MessageBuilder 공통 메서드인 integrate를 통해 Overview와 병합됩니다.
// - 표현할 log는 최신 5 개로 제한합니다.
// - 만약 logRepo가 비어있거나, 한 달 이내 기록이 없다면 "최근 활동 없음" 을 반환합니다.

// [ 예시 ]
//   20240922 14:32  |  [ 비품정리 ] 업무 정보를 제거했습니다.
//   20240922 16:02  |  새로운 팀원 [ 김철수 ] 님을 초대했습니다.
//   20240923 10:10  |  신규 업무 [ 입사자교육준비 ]을(를) 등록했습니다.
//   20240923 17:25  |  [ 김철수 ] 님의 정보를 수정했습니다.
//   20240924 09:50  |  신규 업무 [ 프로그램강사컨택 ]을(를) 등록했습니다.

public class RecentLogsMessageBuilder extends MessageBuilder {

    @Override
    public String build(Pair<String, List<Object>> ingredients) {
        return "";
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}

