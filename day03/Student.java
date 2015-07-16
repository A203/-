package day03;

class Student {
	private String name;
	private String address;
	private String num;
	public Student(String name,String address,String num){
		this.name=name;
		this.address=address;
		this.num=num;
	}
	void zwjs(){
		System.out.println(this.name+" "+this.address+" "+this.num);
	}
	public static void main(String[] args){
		Student s = new Student("ZS","701","2012214292");
		s.zwjs();
	}
}
