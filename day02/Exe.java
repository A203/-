package day02;

import java.util.Random;

public class Exe {
	static int score[][] = new int[20][5];
	static int total[] = new int[20];
	static float average[] = new float[6];
	
	static void initial(){
		Random rd = new Random();
		for(int i=0;i<20;i++)
			for(int j=0;j<5;j++){
				int n = rd.nextInt(100);
				if(n<60){
					n =  rd.nextInt(100);
					if(n<30)
						n =  rd.nextInt(100);
				}	
				score[i][j] = n;
			}
	}
	
	static void caculateTotle(){
		for(int i = 0;i<20;i++){
			total[i]=0;
			for(int j = 0;j<5;j++)
				total[i]+=score[i][j];
		}
				
	}
	static void caculateAverage(){
		for(int i = 0;i< 5;i++){
			average[i]=0;
			for(int j = 0;j<20;j++)
				average[i]+=score[j][i];
			average[i]/=20;
		}
		average[5] = 0;
		for(int i = 0;i<20;i++)
			average[5]+=total[i];
		average[5]/=20;
				
	}
	static void establish(){
		System.out.println("学号\tcore C++\tcoreJava\tServlet\t\tJSP\t\tEJB\t\t总分");
		for(int i=0;i<20;i++){
			System.out.print(i+"\t");
			for(int j=0;j<5;j++)
				System.out.print(score[i][j]+"\t\t");
			System.out.println(total[i]);
		}
		System.out.print("平均分：\t");
		for(int i=0;i<5;i++)
			System.out.print(average[i]+"\t\t");
				
	}
	static void establishTable(){
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("| 学号\t|core C++\t|coreJava\t|Servlet\t|JSP\t\t|EJB\t\t|总分\t|");
		for(int i=0;i<20;i++){
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.print("|"+i+"\t|");
			for(int j=0;j<5;j++)
				System.out.print(score[i][j]+"\t\t|");
			System.out.println(total[i]+"\t|");
		}
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.print("|平均分：\t|");
		for(int i=0;i<5;i++)
			System.out.print(average[i]+"\t\t|");
		System.out.println(average[5]+"\t|\n-------------------------------------------------------------------------------------------------");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		initial();
		caculateTotle();
		caculateAverage();
		establishTable();
	}

}
