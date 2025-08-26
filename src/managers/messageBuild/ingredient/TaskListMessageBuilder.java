package managers.messageBuild.ingredient;

import configs.message.Ingredient;
import managers.messageBuild.MessageBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
            String[] taskInfo = ing.split("/");
            taskInfo[0] = num.incrementAndGet() + "";
            return String.format(format, taskInfo);
        }).reduce(MessageBuilder::integrate).orElseGet(Ingredient.TASK_LIST_FAILED::getFormat);
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}