import java.io.IOException;

/**
 * Tester for LibraryPlotterSalterSmoother.
 * @author Ian Mays
 */
public class TestLibraryPlotterSalterSmoother {
	public static void main(String[] args) throws IOException {
		double a = -5;
		double b = 5;
		int n = 1000;
		double r = 1;
		int m = 10;
		
		LibraryPlotterSalterSmoother pss = new LibraryPlotterSalterSmoother(a, b, n, r, m);
		pss.plot();
		pss.salt();
		pss.smooth();
	}
}
