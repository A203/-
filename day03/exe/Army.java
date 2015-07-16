package day03.exe;

public class Army {
	Weapon wp[] = null;
	int max;
	private int num = 0; 
	public Army(int n){
		wp = new Weapon[n];
		this.max = n;
	}
	public void addWeapon(Weapon wp){
		if(num==max){
			System.out.println("武器库已满");
			return;
		}
		this.wp[num++] = wp;
	}
	public void attackAll(){
		for(int i = 0;i<this.num;i++)
			wp[i].attack();
	}
	public void moveAll(){
		for(int i = 0;i<this.num;i++)
			wp[i].move();
	}
}
