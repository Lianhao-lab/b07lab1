public class Polynomial {
	double[] coefficients;
	public Polynomial() {
		this.coefficients = new double[] {0};
	}
	public Polynomial(double[] array) {
		this.coefficients = new double[array.length];
		for(int i=0; i< array.length; i++)
		{
			this.coefficients[i] = array[i];
		}
	}
	public Polynomial add(Polynomial set) {
		int len_coe = this.coefficients.length;
		int len_set = set.coefficients.length;
		int max = Math.max(len_coe, len_set);
		double[] result = new double[max];
		for(int i = 0; i<max; i++)
		{
			if(i<Math.min(len_set, len_coe)) {
			result[i] = this.coefficients[i] + set.coefficients[i];
			}
			else {
				if(len_set < len_coe) {
					result[i] = this.coefficients[i];
				}
				else {
					result[i] = set.coefficients[i];
				}
			}
		}
		Polynomial final_result = new Polynomial(result);
		return final_result;
	}
	public double evaluate(double x) {
		int len = this.coefficients.length;
		double result = 0;
		for (int i=0; i< len; i++) {
			result += this.coefficients[i] * Math.pow(x, i);
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
