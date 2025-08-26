package managers.messageBuild.ingredient;

import managers.messageBuild.MessageBuilder;

import java.util.List;

// [ MemberListMessageBuilder 클래스 설명 ]
// - MemberListMessageBuilder는 "팀원조회" 기능에서 각 팀원정보를 표현할 Message 제작용 클래스입니다.

// [ 메모 ]
// - Build의 중간 단계에 해당하는 클래스입니다.
// - 제작된 MemberList들은 MessageBuilder 공통 메서드인 integrate를 통해 서로 병합됩니다.

// [ 예시 ]
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//    No.7 [홍길동] Admin | Tasks : 인사관리, 행사준비 (2건)
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//    No.8 [김철수] Member | Tasks : 행사준비, 비품구매 (2건)
//   = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

public class MemberListMessageBuilder extends MessageBuilder {
    // [임시] format용 문자열 보관 위한 필드
    private String format = """
            = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
             No.%d [%s] %s | Tasks : %s (%d건)
            """;

    @Override
    public String build(String format, List<String> ingredients) {
        return "";
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}