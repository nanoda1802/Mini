package utils.console;

public class Viewer {

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void clear() {
        System.out.println("\n".repeat(50));
    }
}

// [ Viewer 클래스 설명 ]
// - Viewer는 콘솔 화면을 코드상에서 조작하기 위한 클래스임다.
// - print() 메서드는 문자열을 받아 println으로 콘솔화면에 출력합니다.
// - clear() 메서드는 100회의 줄바꿈을 통해 보여지는 콘솔화면을 정돈합니다.