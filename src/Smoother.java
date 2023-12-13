import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manual implementation of smoothing step of Plotter, Salter, and Smoother.
 * @author Ian Mays
 */
public class Smoother {
	/**
	 * Smooths input data from salted.csv and saves the result to smoothed.csv.
	 * @param range The size of the moving average window, on each side of the point
	 * being smoothed
	 * @throws FileNotFoundException if salted.csv or smoothed.csv are missing
	 */
	public void smooth(int range) throws FileNotFoundException {
		Scanner in = new Scanner(new File("salted.csv"));
		PrintWriter out = new PrintWriter(new File("smoothed.csv"));
		
		ArrayList<Double> xs = new ArrayList<>();
		ArrayList<Double> ys = new ArrayList<>();
		
		while (in.hasNextLine()) {
			String[] nextLineValues = in.nextLine().split(",");
			
			xs.add(Double.parseDouble(nextLineValues[0]));
			ys.add(Double.parseDouble(nextLineValues[1]));
		}
		
		for (int i = 0; i < ys.size(); i++) {
			List<Double> nearbyYs;
			double sum = 0;
			
			if (i < range) {
				nearbyYs = ys.subList(0, i+range+1);
			} else if (i + range >= ys.size()) {
				nearbyYs = ys.subList(i-range, ys.size()-1);
			} else {
				nearbyYs = ys.subList(i-range, i+range+1);
			}
			
			for (int j = 0; j < nearbyYs.size(); j++) {
				sum += nearbyYs.get(j);
			}
			
			double average = sum / nearbyYs.size();
			
			out.println(xs.get(i) + "," + average);
		}
		
		in.close();
		out.close();
	}
}
