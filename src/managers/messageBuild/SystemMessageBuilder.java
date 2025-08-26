package managers.messageBuild;

import utils.Pair;

import java.util.List;

// [ SystemMessageBuilder 클래스 설명 ]
// - SystemMessageBuilder는 SystemMessage 제작을 위한 클래스입니다.

// [ 메모 ]
// - UiMessageBuilder와 SystemMessageBuilder는 Build의 완성 단계에 해당합니다.

// [ build 메서드 해석 ]
// - ingredients.getKey() : format이 되어줄 문자열 추출
// - ingredients.getValue().stream() : 재료가 되어줄 값들 추출하고, 스트림 준비
// - map(ing -> (String) ing) : 재료들은 Object로 포장돼있기 때문에, String으로의 다운캐스팅을 위한 map
// - toArray() : String.format의 두번째 인자는 가변인자임. 여러 값을 동시에 넣기 위해 Collection -> Array로 변환

public class SystemMessageBuilder extends MessageBuilder {
    @Override
    public String build(Pair<String, List<String>> ingredients) {
        return String.format(ingredients.getKey(), ingredients.getValue().toArray());
    }

    @Override
    public String build(String ingredient) {
        return ingredient;
    }
}
