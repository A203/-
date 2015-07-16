package day03;

class Phone {
	String name;
	String brand;
	int price;

	void call() {
		System.out.println("打电话。。。。。");
	}

	void sendMag() {
		System.out.println("发短信。。。。。");
	}

	public static void main(String[] args) {
		Phone p = new Phone();
		p.name="note";
		p.brand="爆米花";
		p.price=123;
		p.call();
		p.sendMag();
	}
}
