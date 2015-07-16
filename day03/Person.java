package day03;

class Person {
	String name;
	int age;
	String address;
	Person(String name,int age,String address){
		this.name=name;
		this.age = age;
		this.address=address;
	}
	void show(){
		System.out.println(name+" "+age+" "+address);
	}
	public static void main(String[] args) {
		Person p = new Person("SYM", 19, "702");
		p.show();
	}

}
