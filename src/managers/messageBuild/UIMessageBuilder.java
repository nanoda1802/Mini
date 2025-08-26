package managers.messageBuild;

import java.util.List;

// [ SystemMessageBuilder 클래스 설명 ]
// - SystemMessageBuilder는 SystemMessage 제작을 위한 클래스입니다.

// [ 메모 ]
// - UiMessageBuilder와 SystemMessageBuilder는 Build의 완성 단계에 해당합니다.

public class UIMessageBuilder extends MessageBuilder {
    @Override
    public String build(String format, List<String> ingredients) {
        return String.format(format, ingredients.toArray());
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}