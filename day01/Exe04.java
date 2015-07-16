package day01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Exe04 {

	/**
	 * @param args
	 */
	static ArrayList<String> al = new ArrayList<String>();
	static void show(){
		Iterator<String> it = al.iterator();
		int i = 1;
		while (it.hasNext()) {
			System.out.println("第"+(i++)+"个:"+it.next());
		}
	}
	static void operate(){
		System.out.println("|1，增加1个学生 |");
		System.out.println("|2，显示所有学生|");
		System.out.println("|3，退出程序         |");
		System.out.println("请输入选项（1-3）:");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		while (true) {
			operate();
			String n = sc.next();
			switch(n){
			case "1":
				System.out.println("请输入学院姓名：");
				al.add(sc.next());
				break;
			case "2":
				show();
				break;
			case "3":return;
			default :break;
			}
		}
	}

}
