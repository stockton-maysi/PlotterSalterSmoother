import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Manual implementation of salting step of Plotter, Salter, and Smoother.
 * @author Ian Mays
 */
public class Salter {
	/**
	 * Salts input data from plot.csv and saves the result to salted.csv.
	 * @param range The maximum range of deviation due to salting
	 * @throws FileNotFoundException if plot.csv or salted.csv are missing
	 */
	public void salt(double range) throws FileNotFoundException {
		Scanner in = new Scanner(new File("plot.csv"));
		PrintWriter out = new PrintWriter(new File("salted.csv"));
		
		Random random = new Random();
		
		while (in.hasNextLine()) {
			String[] nextLineValues = in.nextLine().split(",");
			
			double x = Double.parseDouble(nextLineValues[0]);
			double y = Double.parseDouble(nextLineValues[1]);
			double r = random.nextDouble(-range, range);
			
			out.println(x + "," + (y+r));
		}
		
		in.close();
		out.close();
	}
}