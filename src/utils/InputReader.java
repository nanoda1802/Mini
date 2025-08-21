package utils;

import java.util.Scanner;

public class InputReader {
	private static final Scanner reader = new Scanner(System.in);

	public static String readInput() {
		return reader.nextLine().replaceAll(" ", "");
	};
}
