package managers.conversion;

// [ Converter 클래스 설명 ]
// - Converter는 Converter류 클래스들의 구현 기반을 마련하기 위한 추상 클래스입니다.

public abstract class Converter<T, R> {
    // [정방향 변환] ABConverter면 A -> B
    public abstract R convertTo(T target);

    // [역방향 변환] ABConverter면 B -> A
    public abstract T convertFrom(R target);
}

