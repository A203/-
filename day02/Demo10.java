package day02;

import java.util.Date;
import java.util.Scanner;

public class Demo10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = new Scanner(System.in).nextInt();
		//第一种方法
		Date d1 = new Date();
		for(int i=1;i<=n;i++){
			int m=i*2-1;
			for(int j=0;j<n-i;j++)
				System.out.print(" ");
			for(int j=1;j<=m;j++)
				System.out.print("*");
			System.out.println("");
		}
		Date d2 = new Date();
		System.out.println("所用时间："+(d2.getTime()-d1.getTime())+"毫秒");
		//第二种方法
		String s1 = " ";
		String s2 = "*";
		for(int i=1;i<n;i++){
			s1+=" ";
		}
		for(int i=1;i<=n;i++){
			s1=s1.substring(1);
			System.out.print(s1);
			System.out.println(s2);
			s2+="**";
		}
		Date d3 = new Date();
		System.out.println("所用时间："+(d3.getTime()-d2.getTime())+"毫秒");
	}

}
