package utils;

// [ Graph 클래스 설명 ]
// - Graph는 홈화면의 overview에 활용될 간단한 그래프를 그리는 클래스임다.

// [ 메모 ]
// 계산 복잡도가 높아지는 걸 경계해 십분율로 계산하도록 했슴다.

public class Graph {
    public static String draw(int cnt, int total) {
        int ratioByTen = (int) Math.floor((double) cnt / total * 10);
        return "■".repeat(ratioByTen) + "□".repeat(10 - ratioByTen) + " | " + ratioByTen * 10 + "%";
    }
}
