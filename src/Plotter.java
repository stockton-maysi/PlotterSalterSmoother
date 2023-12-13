import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Manual implementation of plotting step of Plotter, Salter, and Smoother.
 * @author Ian Mays
 */
public class Plotter {
	/**
	 * The function being plotted.
	 * @param x The input value
	 * @return sin(x^2)
	 */
	public double f(double x) {
		return Math.sin(x*x);
	}
	
	/**
	 * Plots the function and saves the raw results to plot.csv.
	 * @param a The left boundary for the range of x values
	 * @param b The right boundary for the range of x values
	 * @param n The number of points to plot
	 * @throws FileNotFoundException if plot.csv is missing
	 */
	public void plot(double a, double b, int n) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("plot.csv"));
		
		for (int i = 0; i < n; i++) {
			double x = a + (b-a)*i/n;
			
			out.println(x + "," + f(x));
		}
		
		out.close();
	}
}