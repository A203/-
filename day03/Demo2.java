package day03;

public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		C c1 = new C();
		C c2 = new C();
		System.out.println(c1.num1);
		System.out.println(c1.num2);
		c1.a();
		System.out.println(c1.num1);
		System.out.println(c1.num2);
		System.out.println(c2.num1);
		System.out.println(c2.num2);
	}

}
class C{
	static int num1 = 1;
	int num2 = 2;
	void a(){
		num1 = 9;
		num2 = 10;
	}
}