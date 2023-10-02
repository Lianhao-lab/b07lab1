import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {7,6,4};
		int[] e1 = {0,2,1};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {3,1,-5,2,-5};
		int[] e2 = {0,3,1,2,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		int lens = s.exponents.length;
		System.out.println("exponents:");
		for(int i=0; i< lens; i++) {
			System.out.println(s.exponents[i]);
		}
		System.out.println("coefficients:");
		for(int i=0; i< lens; i++) {
			System.out.println(s.coefficients[i]);
		}
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		
		Polynomial s1 = p1.multiply(p2);
		int lens_s1 = s1.exponents.length;
		for(int i=0; i< lens_s1; i++) {
			System.out.println(s1.exponents[i]);
		}
		System.out.println("coefficients:");
		for(int i=0; i< lens_s1; i++) {
			System.out.println(s1.coefficients[i]);
		}
		
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		File file = new File("D:\\University of Toronto\\Second Year\\CSCB07\\b07lab1\\B07_lab_1\\src\\111.txt");
		try {
			Polynomial d = new Polynomial(file);
			for (int i = 0; i < d.coefficients.length; i++) {
				System.out.println(d.coefficients[i]);
				System.out.println(d.exponents[i]);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		s1.saveToFile("D:\\University of Toronto\\Second Year\\CSCB07\\b07lab1\\B07_lab_1\\src\\text.txt");


}
	
	
	
	
	
	
	
	
	
	
}