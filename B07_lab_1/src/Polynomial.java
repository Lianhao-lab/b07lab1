import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
	double[] coefficients;
	int[] exponents;
	public Polynomial() {
		this.coefficients = new double[] {0};
		this.exponents = new int[] {0};
	}
	public Polynomial(double[] array, int[] exponents) {
		this.coefficients = new double[array.length];
		this.exponents = new int[exponents.length];
		for(int i=0; i< array.length; i++)
		{
			this.coefficients[i] = array[i];
			this.exponents[i] = exponents[i];
		}
	}

	public Polynomial(File file) throws IOException {
		Scanner input = new Scanner(file);
		String message = input.next();
		String[] array = message.split("(?=[+-])");
		int len = array.length;
		this.coefficients = new double[len];
		this.exponents = new int[len];
		for(int i = 0; i<len; i++) {
			if(array[i].length()>1) {
				int location = array[i].indexOf('x');
				this.coefficients[i] = Double.parseDouble(array[i].substring(0,location));
				this.exponents[i] = Integer.parseInt(array[i].substring(location+1));
			}else {
				this.coefficients[i] = Double.parseDouble(array[i]);
				this.exponents[i] = 0;
			}
		}
		input.close();
	}

	public boolean check(int num, int[] target_array) {
		int len = target_array.length;
		for(int i=0; i< len; i++) {
			if(num == target_array[i]) {
				return false;
			}
		}
		return true;
	}
	
	public int check_place(int num, int[] target_array) {
		int len = target_array.length;
		int place = -1;
		for(int i=0; i< len; i++) {
			if(num == target_array[i]) {
				place = i;
				break;
			}
		}
		return place;
	}
	
	public Polynomial redundant(Polynomial result) {
		int len = result.coefficients.length;
		int count_zero = 0;
		for(int i = 0; i< len; i++) {
			if(result.coefficients[i] == 0) {
				count_zero ++;
			}
		}
		int size = len - count_zero;
		double[] new_coefficient = new double[size];
		int[] new_exponent = new int[size];
		int order = 0;
		for(int i=0; i<len; i++) {
			if(result.coefficients[i]!=0) {
				new_coefficient[order] = result.coefficients[i];
				new_exponent[order] = result.exponents[i];
				order++;
			}
		}
		return new Polynomial(new_coefficient,new_exponent);
	}
	
	public Polynomial add(Polynomial set) {
		int len_expon = this.exponents.length;
		int len_set_expon = set.exponents.length;
		int longer = Math.max(len_expon, len_set_expon);
		int count = longer;
		for(int i = 0; i < len_expon; i++)
		{
			int test = 0;
			for(int j=0; j < len_set_expon; j++) {
				if(this.exponents[i] == set.exponents[j])
				{
					test += 1;
				}
			}
			if(test == 0) {
				count++;
			}
		}
		int[] result_exponents = new int[count];
		double[] result_coefficients = new double[count];
		for(int i = 0; i<len_expon; i++) {
			result_exponents[i] = this.exponents[i];
			result_coefficients[i] = this.coefficients[i];
		}
		int place = 0;
		if(len_expon != count) {
			for(int j=0; j<len_set_expon; j++) {
				if(check(set.exponents[j], result_exponents)) {
					result_exponents[len_expon + place] = set.exponents[j];
					place += 1;
				}
			}
		}
		int place_coeff = 0;
		for(int i = 0; i < len_set_expon; i++) {
			int order = check_place(set.exponents[i],result_exponents);
			if(order != -1) {
				result_coefficients[order] +=set.coefficients[i];
			}
			else {
				result_coefficients[len_expon + place_coeff] = set.coefficients[i];
				place_coeff ++;
			}
		}
		Polynomial result = new Polynomial(result_coefficients,result_exponents);
		Polynomial true_result = redundant(result);
		return true_result;
	}

	public Polynomial multiply(Polynomial set) {
		double[] result_coefficients = {0.0};
		int[] result_exponents = {0};
		Polynomial result = new Polynomial(result_coefficients, result_exponents);
		int len_set = set.coefficients.length;
		for(int i = 0; i < this.coefficients.length; i++) {
			int[] new_exponents = new int[len_set];
			double[] new_coefficients = new double[len_set];
			for(int j = 0; j < len_set; j++) {
				new_coefficients[j] = this.coefficients[i] * set.coefficients[j];
				new_exponents[j] = this.exponents[i] + set.exponents[j];
			}
			Polynomial new_add = new Polynomial(new_coefficients, new_exponents);
			result = result.add(new_add);
		}
		return result;
	}
	
	public void saveToFile(String address) throws IOException {
		FileWriter poly = new FileWriter(address);
		String result = "";
		int len = this.exponents.length;
		for(int i=0; i<len;i++) {
			if(this.exponents[i]!=0) {
				result = result + "+" + Double.toString(this.coefficients[i]) + "x" + Integer.toString(this.exponents[i]);
			}else {
				result = result + "+" + Double.toString(this.coefficients[i]);
			}
		}
		String final_result = result;
		int len_result = result.length();
		int trace = 0;
		for(int i = 0; i < len_result; i++) {
			if(final_result.charAt(i) == '+') {
				if(i==0) {
					result = result.substring(1);
					trace+=1;
				}else if(final_result.charAt(i)=='+' && final_result.charAt(i+1)=='-') {
					result = result.substring(0,i-trace)+result.substring(i-trace+1);
					trace++;
				}
			}
		}
		poly.write(result);
		poly.close();
	}
	
	public double evaluate(double x) {
		int len = this.coefficients.length;
		double result = 0;
		for (int i=0; i< len; i++) {
			result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return result;
	}
	public boolean hasRoot(double x) {
		double result = evaluate(x);
		if(result == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
