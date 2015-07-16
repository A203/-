package day02;

public class Demo5 {

	/**
	 * @param args
	 */
	public enum day{
		Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
	};
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		day c = day.Monday;
		int i=0;
		switch(c){
		case Monday:i=1;break;
		case Tuesday:i=2;break;
		case Wednesday:i=3;break;
		case Thursday:i=4;break;
		case Friday:i=5;break;
		case Saturday:i=6;break;
		case Sunday:i=7;break;
		}
		System.out.println(i);
	}

}
