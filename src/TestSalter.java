import java.io.FileNotFoundException;

/**
 * Tester for Salter.
 * @author Ian Mays
 */
public class TestSalter {
	public static void main(String[] args) throws FileNotFoundException {
		double range = 1;
		
		Salter salter = new Salter();
		salter.salt(range);
	}
}