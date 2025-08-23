package managers.messageBuild.ingredient;

import managers.messageBuild.MessageBuilder;
import utils.Pair;

import java.util.List;

// [ OverviewMessageBuilder 클래스 설명 ]
// - OverviewMessageBuilder는 홈 화면에 활용되는 "프로젝트 현황" 항목 제작을 위한 클래스입니다.

// [ 메모 ]
// - Build의 중간 단계에 해당하는 클래스입니다.
// - 제작된 Overview는 MessageBuilder 공통 메서드인 integrate를 통해 RecentLogs와 병합됩니다.

public class OverviewMessageBuilder extends MessageBuilder {
    // [임시] format용 문자열 보관 위한 필드
    private String format = """
            [업무상태] 완료 %d | 진행 %d | 대기 %d | 전체 %d
            [분업평가] %s | 업무보유율=■ | 미보유율=□
                     가장 업무가 많은 팀원은 %s님이며, 현재 분업 현황 점수는 %.1f 점입니다.
            """;

    @Override
    public String build(Pair<String, List<Object>> ingredients) {
        return "";
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}


// [요약] 업무현황 : [ 완료 %d | 진행 %d | 대기 %d | 전체 %d ] | 업무중인 인원 : %d/%d
// [상태] ■■■▦▦▦▦▦□□ [진척도 30%] [ 완료=■ | 진행=▦ | 대기=□ ]
// [분업] 가장 업무가 많은 팀원은 %s님이며, 현재 분업 현황 점수는 %.1f 점입니다.		