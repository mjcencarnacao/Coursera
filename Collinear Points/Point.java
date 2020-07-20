import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {

		double newX = that.x - this.x;
		double newY = that.y - this.y;
		
		// degenerate line
		if ((newX == 0 && newY == 0))
			return Double.NEGATIVE_INFINITY;

		// vertical line
		if (newX == 0)
			return Double.POSITIVE_INFINITY;

		// horizontal line
		if (newY == 0)
			return 0;

		return newY / newX;
	}

	public int compareTo(Point that) {
		if (that.y - this.y == 0)
			return this.x - that.x;
		return this.y - that.y;
	}

	public Comparator<Point> slopeOrder() {
		return Comparator.comparingDouble(this::slopeTo);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static void main(String[] args) {
	}

}