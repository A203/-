package day02;

import java.util.Scanner;

public class DEmo11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = (new Scanner(System.in)).nextInt();
		String s1 = " ";
		for(int i=1;i<n;i++){
			s1+=" ";
		}
		for (int i = 1; i <= n; i++) {
			s1=s1.substring(1);
			System.out.print(s1);
			for (int j = 0; j < i*2-1; j++) {
				if (i < 10) {
					System.out.print(i);
				} else {
					System.out.print((char) ('A' + i - 10));
				}
			}
			System.out.println();
		}
	}
}
