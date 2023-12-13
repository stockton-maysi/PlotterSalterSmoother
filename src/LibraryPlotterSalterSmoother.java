import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.math4.legacy.stat.StatUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * Implementation of Plotter/Salter/Smoother using Apache Stats Library and JFreeChart.
 * @author Ian Mays
 */
public class LibraryPlotterSalterSmoother {
	Random random = new Random();
	
	private DefaultXYDataset dataset;
	private double a;
	private double b;
	private int n;
	private double r;
	private int m;
	
	/**
	 * Constructs a new LibraryPlotterSalterSmoother
	 * @param a The left boundary for the range of x values
	 * @param b The right boundary for the range of x values
	 * @param n The number of points to plot
	 * @param r The maximum magnitude of salting
	 * @param m The size of the moving average window for smoothing
	 */
	public LibraryPlotterSalterSmoother(double a, double b, int n, double r, int m) {
		dataset = new DefaultXYDataset();
		this.a = a;
		this.b = b;
		this.n = n;
		this.r = r;
		this.m = m;
	}
	
	/**
	 * The function being plotted.
	 * @param x The input value
	 * @return sin(x^2)
	 */
	public double f(double x) {
		return Math.sin(x*x);
	}
	
	/**
	 * Plots f(x) and saves the result to plot.png as a chart.
	 */
	public void plot() throws IOException {
		double[][] data = new double[2][n];
		
		for (int i = 0; i < data[0].length; i++) {
			double x = a + (b-a)*i/n;
			data[0][i] = x;
			data[1][i] = f(x);
		}
		
		dataset.addSeries(0, data);
		JFreeChart chart = ChartFactory.createScatterPlot("f(x) = sin(x^2), n=" + n, "x", "f(x)", dataset);
		ChartUtilities.saveChartAsPNG(new File("plot.png"), chart, 1000, 500);
	}
	
	/**
	 * Salts f(x), increasing each y value by a random number between -r and r, and
	 * then saves the result to salted.png as a chart.
	 */
	public void salt() throws IOException {
		double[][] saltedData = new double[2][n];
		
		for (int i = 0; i < n; i++) {
			double x = dataset.getXValue(0, i);
			double y = dataset.getYValue(0, i);
			saltedData[0][i] = x;
			saltedData[1][i] = y + random.nextDouble();
		}
		
		dataset.removeSeries(0);
		dataset.addSeries(0, saltedData);
		JFreeChart chart = ChartFactory.createScatterPlot("f(x) = sin(x^2) after salting, n=" + saltedData[0].length + ", r=" + r, "x", "f(x)", dataset);
		ChartUtilities.saveChartAsPNG(new File("salted.png"), chart, 1000, 500);
	}
	
	/**
	 * Smooths the salted data with a moving average window of m points on each side,
	 * and then saves the result to smoothed.png as a chart.
	 * @throws IOException
	 */
	public void smooth() throws IOException {
		double[][] smoothedData = new double[2][n];
		double[] ys = new double[n];
		
		for (int i = 0; i < smoothedData[0].length; i++) {
			double x = dataset.getXValue(0, i);
			ys[i] = dataset.getYValue(0, i);
			
			int leftBound;
			int rightBound;
			
			if (i < m) {
				leftBound = 0;
				rightBound = i+m+1;
			} else if (i + m >= n) {
				leftBound = i-m;
				rightBound = n;
			} else {
				leftBound = i-m;
				rightBound = i+m+1;
			}
			
			smoothedData[0][i] = x;
			smoothedData[1][i] = StatUtils.mean(ys, leftBound, rightBound-leftBound);
		}
		
		dataset.removeSeries(0);
		dataset.addSeries(0, smoothedData);
		JFreeChart chart = ChartFactory.createScatterPlot("f(x) = sin(x^2) after smoothing, n=" + smoothedData[0].length + ", r=" + r + ", m=" + m, "x", "f(x)", dataset);
		ChartUtilities.saveChartAsPNG(new File("smoothed.png"), chart, 1000, 500);
	}
}