package managers.messageBuild.ingredient;

import managers.messageBuild.MessageBuilder;
import utils.Pair;

import java.util.List;

// [ TaskListMessageBuilder 클래스 설명 ]
// - TaskListMessageBuilder는 "업무조회" 기능에서 각 업무정보를 표현할 Message 제작용 클래스입니다.

// [ 메모 ]
// - Build의 중간 단계에 해당하는 클래스입니다.
// - 제작된 TaskList들은 MessageBuilder 공통 메서드인 integrate를 통해 서로 병합됩니다.

// [ 예시 ]
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//    No.22 [ 1주년행사준비 ]
//     유형 : 기획  |  상태 : 대기중  |  담당자 : 미정
//     기간 : 2024.09.12 ~ 2024.09.31
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//    No.23 [ 탕비실청소 ]
//     유형 : 기타  |  상태 : 진행중  |  담당자 : 홍길동
//     기간 : 2024.05.31 ~ 미정
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

public class TaskListMessageBuilder extends MessageBuilder {
    // [임시] format용 문자열 보관 위한 필드
    private String format = """
            = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
             No.%d [ %s ]
              유형 : %s  |  상태 : %s  |  담당자 : %s
              기간 : %s ~ %s
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