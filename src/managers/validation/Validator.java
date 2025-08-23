package managers.validation;

import utils.Pair;

// [ Validator 클래스 설명 ]
// - Validator는 Validator류 클래스들의 구현 기반을 마련하기 위한 추상 클래스입니다.

// [ 메모 ]
// - Pair 클래스에 대한 설명은 utils.Pair.java 파일에 있습니다!

public abstract class Validator {
    public abstract Pair<Boolean, String> check(String target);
}