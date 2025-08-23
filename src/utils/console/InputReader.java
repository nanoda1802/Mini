package utils.console;

import java.util.Scanner;

public class InputReader {
    private static final Scanner reader = new Scanner(System.in);

    public static String read() {
        return reader.nextLine().replaceAll(" ", "");
    }
}

// [ InputReader 클래스 설명 ]
// - InputReader는 콘솔화면에서 사용자의 입력을 받기 위한 클래스입니다.
// - 시스템 이용을 위해 Scanner 타입의 reader 필드를 갖습니다.
// - read() 메서드는 사용자의 입력 한 줄을 받아 공백을 제거한 String을 반환합니다.