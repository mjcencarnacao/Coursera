package Week1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] arrayResults;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException(" n and trials must be positive and higher than 0");
		arrayResults = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation trial = new Percolation(n);
			while (!trial.percolates()){
				trial.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
			}
			double result = (double)trial.numberOfOpenSites() / (n * n);
			arrayResults[i] = result;
		}

	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(arrayResults);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(arrayResults);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(arrayResults.length));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(arrayResults.length));
	}

	// test client (see below)
	public static void main(String[] args) {

		PercolationStats trials = new PercolationStats(200, 100);

		StdOut.println("mean = " + trials.mean());
		StdOut.println("stddev = " + trials.stddev());
		String interval = trials.confidenceLo() + ", " + trials.confidenceHi();
		StdOut.println("95% confidence interval = " + interval);
	}
}
