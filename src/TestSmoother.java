import java.io.FileNotFoundException;

/**
 * Tester for Smoother.
 * @author Ian Mays
 */
public class TestSmoother {
	public static void main(String[] args) throws FileNotFoundException {
		int range = 10;
		
		Smoother smoother = new Smoother();
		smoother.smooth(range);
	}
}