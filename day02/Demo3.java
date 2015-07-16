package day02;

import java.util.Scanner;

public class Demo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n<0 || n>100){
			System.out.println("输入错误");
		}
		else{
			if(n>=60){
				System.out.println("成绩合格");
			}
			else{
				System.out.println("成绩不合格");
			}
		}
	}

}
