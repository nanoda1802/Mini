package managers.messageBuild;

import utils.Pair;

import java.util.List;

// [ SystemMessageBuilder 클래스 설명 ]
// - SystemMessageBuilder는 SystemMessage 제작을 위한 클래스입니다.

// [ 메모 ]
// - UiMessageBuilder와 SystemMessageBuilder는 Build의 완성 단계에 해당합니다.

public class UIMessageBuilder extends MessageBuilder {
    @Override
    public String build(Pair<String, List<String>> ingredients) {
        return String.format(ingredients.getKey(), ingredients.getValue().toArray());
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }

    // [임시] format용 문자열과 재료 element 간에 타입 오류 발생 대비 메서드...
    private void checkFormat(String format) {
        // [ 첫 번째 안 ]
        // [1] format용 문자열을 순회하며 %d, %s, %f 등의 투입 위치를 확인
        // [2] 몇 번째 위치에 어떤 타입이 들어가야하는지 기록
        // [3] 기록된 정보를 반환하고, 외부에서 재료 List와 타입 비교

        // [ 두 번째 안 ]
        // [1] try-catch On...
        // [2] 그냥 냅다 format 문자열에 재료 element들 넣어버림
        // [3] 오류 발생 시 throw 해서 외부에 불일치 사유 전달
    }
}