package controller;

// [ Adder 인터페이스 설명 ]
// - Adder는 Controller류 클래스들에게 CRUD 중 Create를 제공하는 인터페이스입니다.
// - Controller의 필요에 따라 기능을 효율적으로 구현하기 위해 분리됐습니다. (IFS원칙)

public interface Adder<T> {
    T add(String[] args);
}
