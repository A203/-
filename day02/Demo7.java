package day02;

public class Demo7 {
	public static void main(String args[]){
		int num = 0;
		for(int i = 1;i<=100;i++){
			if(i%5==0){
				System.out.println(i);
				continue;
			}
			num++;
		}
		System.out.println(num);
	}
}
