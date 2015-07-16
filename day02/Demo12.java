package day02;

public class Demo12 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] a = { 4, 6, 2, 6, 3, 45, 3, 4, 324, 2213, 45 };
		for (int i = a.length; i > 0; i--)
			for (int j = 0; j < i-1; j++) {
				if (a[j] > a[j + 1]) {
					int t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
				}

			}
		for(int n:a){
			System.out.print(n+"\t");
		}
	}

}
