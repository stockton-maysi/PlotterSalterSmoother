import java.io.FileNotFoundException;

/**
 * Tester for Plotter.
 * @author Ian Mays
 */
public class TestPlotter {
	public static void main(String[] args) throws FileNotFoundException {
		double a = -5;
		double b = 5;
		int n = 1000;
		
		Plotter plotter = new Plotter();
		plotter.plot(a, b, n);
	}
}