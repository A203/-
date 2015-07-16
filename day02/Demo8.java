package day02;

import java.util.Date;

public class Demo8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Date d1 = new Date();
		for(int i = 2;i<1000000;i++){
			boolean flag = true;
			for(int j=2;j<=(int)Math.sqrt(i);j++){
				if(i%j==0){
					flag = false;
					break;
				}			
			}
			if (flag == true) {
				System.out.println(i);
				i++;
			}
		}
		Date d2 = new Date();
		System.out.println("所用时间："+(d2.getTime()-d1.getTime())+"毫秒");
	}

}
