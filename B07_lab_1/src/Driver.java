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
		
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		
		
		s.saveToFile("111.txt");
      


}
	
	
	
	
	
	
	
	
	
	
}