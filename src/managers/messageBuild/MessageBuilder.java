package managers.messageBuild;

import utils.Pair;

import javax.management.ObjectName;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// [ MessageBuilder 클래스 설명 ]
// - MessageBuilder는 MessageBuilder류 클래스들의 구현 기반을 마련하기 위한 추상 클래스입니다.

public abstract class MessageBuilder {
    public abstract String build(Pair<String, List<String>> ingredients);

    // [임시] 재료 없이 완성되는 Message 사례를 위해 build 메서드를 overload
    public abstract String build(String ingredient);

    // [임시] 재료들을 Pair의 value에 넣기 위해 하나의 List로 포장하는 메서드
    public List<String> pack(Object... raws) {
        return Arrays.stream(raws).map(i -> Objects.toString(i,"(pack 오류)")).toList();
    }

    // [임시] 제작된 Message들을 줄바꿈으로 이어진 하나의 Message로 결합하는 메서드
    public String integrate(String... msgs) {
        return Arrays.stream(msgs).reduce((acc, cur) -> acc + "\n" + cur).orElseGet(() -> "메세지 생성 실패");
    }
}
