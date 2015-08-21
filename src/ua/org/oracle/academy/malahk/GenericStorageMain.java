package ua.org.oracle.academy.malahk;

public class GenericStorageMain {

	public static void main(String[] args) {

		Integer a = 0;
		int b = 1;
		int c = 2;
		int d = 3;
		int e = 4;
		int f = 4;


		GenericStorage<Integer> myData = new GenericStorage<>();
		myData.add(a);
		myData.add(b);
		myData.add(c);
		myData.add(d);
		myData.add(e);
		myData.add(f);

		System.out.println(myData.getPreLast((Integer i1, Integer i2) -> i1.compareTo(i2)));



	}

}
