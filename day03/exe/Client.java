package day03.exe;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Army am = new Army(5);
		am.addWeapon(new Tank());
		am.addWeapon(new Flighter());
		am.addWeapon(new WarShip());
		am.addWeapon(new Tank());
		am.addWeapon(new Flighter());
		am.addWeapon(new WarShip());
		am.moveAll();
		System.out.println("-------------------------------");
		am.attackAll();
	}

}
