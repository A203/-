package day02;

public class Demo6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int i = 1;
		while(i<=100)
			System.out.println(i++);
		for(int j = 1;j<=100;j++)
			System.out.println(j);
		i = 1;
		do{
			System.out.println(i++);
		}while(i<=100);
		int a[] = {4,6,2,7,3,1,7,8,90,5,4,343};
		for(int n: a){
			System.out.println(n);
		}
	}

}
