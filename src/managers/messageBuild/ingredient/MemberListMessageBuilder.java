package managers.messageBuild.ingredient;

import configs.message.Ingredient;
import managers.messageBuild.MessageBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
//    private String format = """
//            = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//             No.%s [%s] %s | Tasks : %s (%d건)
//            """;

    @Override
    public String build(String format, List<String> ingredients) {
        // [메모] 람다에서도 넘버링하기 위한 변수 선언
        AtomicInteger num = new AtomicInteger(0);

        // [설명]
        // - ingredients는 각 Tasks들의 정보를 요약한 String들의 리스트
        // - map을 통해 각 정보들을 순회하며 TaskList의 포맷에 맞게 변환
        // - reduce를 통해 변환한 문자열들을 이어 붙임
        // - 적절한 재료가 주어지지 않았을 경우 실패 케이스 문자열 반환
        return ingredients.stream().map(ing -> {
            String[] memberInfo = ing.split("/");
            memberInfo[0] = num.incrementAndGet() + "";
            return String.format(format, memberInfo);
        }).reduce(MessageBuilder::integrate).orElseGet(Ingredient.MEMBER_LIST_FAILED::getFormat);
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}