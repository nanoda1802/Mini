package utils;


public class MakeGraph {
    public static String draw(int cnt1, int total) {
        int ratioByTen = (int) Math.floor((double) cnt1 / total * 10);
        return "■".repeat(ratioByTen) + "□".repeat(10 - ratioByTen) + " | " + ratioByTen + "%";
    }
}
