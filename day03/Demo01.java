package day03;

public class Demo01 {
	int age;
	static String name = "SYM";
	void hello(){
		System.out.println("hello....");
	}
	static void h1(){
		System.out.println("h1....");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Demo01 d1 = new Demo01();
		d1.hello();
		d1.h1();
		Demo01.h1();
		System.out.println(Demo01.name);
	}

}
