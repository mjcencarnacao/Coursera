package Week1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF TBUF;
	private WeightedQuickUnionUF TUF;
	private boolean[][] Grid;
	private int Width;
	private int openSites;
	private int VTop;
	private int VBottom;

	// Site Index on the Grid
	private int getIndex(int x, int y) {
		return Width * (x - 1) + y;
	}

	// Validates Position
	private boolean validate(int row, int col) {
		if (row <= 0 || col <= 0 || row > Grid.length || col > Grid.length)
			return false;

		return true;
	}

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n must be higher than 0");
		Grid = new boolean[n][n];
		TBUF = new WeightedQuickUnionUF(n * n + 2);
		TUF = new WeightedQuickUnionUF(n * n + 1);
		Width = n;
		openSites = 0;
		VTop = 0;
		VBottom = n * n + 1;

	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (isOpen(row, col))
			return;

		int Index = getIndex(row, col);

		Grid[row - 1][col - 1] = true;
		openSites++;

		if (row == 1) {
			TBUF.union(VTop, Index);
			TUF.union(VTop, Index);
		}

		if (row == Width)
			TBUF.union(VBottom, Index);

		// Connects if the Top site is open
		if (validate(row - 1, col) && isOpen(row - 1, col)) {
			TBUF.union(Index, getIndex(row - 1, col));
			TUF.union(Index, getIndex(row - 1, col));
		}

		// Connects if the Bottom site is open
		if (validate(row + 1, col) && isOpen(row + 1, col)) {
			TBUF.union(Index, getIndex(row + 1, col));
			TUF.union(Index, getIndex(row + 1, col));
		}

		// Connects if the Left site is open
		if (validate(row, col - 1) && isOpen(row, col - 1)) {
			TBUF.union(Index, getIndex(row, col - 1));
			TUF.union(Index, getIndex(row, col - 1));
		}

		// Connects if the Right site is open
		if (validate(row, col + 1) && isOpen(row, col + 1)) {
			TBUF.union(Index, getIndex(row, col + 1));
			TUF.union(Index, getIndex(row, col + 1));
		}

	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		validate(row, col);
		return Grid[row - 1][col - 1];

	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		validate(row, col);
		return TUF.find(VTop) == TUF.find(getIndex(row, col));
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		return TBUF.find(VTop) == TBUF.find(VBottom);
	}

}
