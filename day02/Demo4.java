package day02;

import java.util.Scanner;

public class Demo4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = null;
		switch (n) {
		case 1:
			s = "Monday";
			break;
		case 2:
			s = "Tuesday";
			break;
		case 3:
			s = "Wednesday";
			break;
		case 4:
			s = "Thursday";
			break;
		case 5:
			s = "Friday";
			break;
		case 6:
			s = "Saturday";
			break;
		case 7:
			s = "Sunday";
			break;
		default:
			s = "error day";
		}
		System.out.println(s);
	}

}
