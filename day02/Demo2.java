package day02;

import java.util.Scanner;

public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n>=90)
			System.out.println("等级为A");
		else if(n>=80)
			System.out.println("等级为B");
		else if(n>=70)
			System.out.println("等级为C");
		else if(n>=60)
			System.out.println("等级为D");
		else 
			System.out.println("等级为E");
	}

}
