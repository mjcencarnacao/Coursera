import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {

	/*
	 *  BruteCollinearPoints checks in one go if 4 points have the same slope, this
	 *  means they are all in the same line segment. If 4 points appear on a line
	 *  segment in the order p→q→r→s, then we need to include either the line segment
	 *  p→s or s→p (but not both) and you should not include subsegments such as p→r
	 *  or q→r. To check whether the 4 points p, q, r, and s are collinear, we need
	 *  to check whether the three slopes: between p and q; between p and r; between
	 *  p and s are all equal.
	 */

	private final Point[] fourPointsArray;
	private final ArrayList<LineSegment> segments;
	private int N;

	// finds all line segments containing 4 or more points
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new NullPointerException("Argument can not be null.");
		int aux = 0;
		for (Point p : points) {
			if (p == null)
				throw new NullPointerException("The point from the" + aux + " position can not be null.");
			aux++;
		}
		fourPointsArray = points.clone();
		segments = new ArrayList<>();
		N = fourPointsArray.length;
		Arrays.sort(fourPointsArray);
		for (int i = 0; i < fourPointsArray.length - 1; i++)
			if (fourPointsArray[i].compareTo(fourPointsArray[i + 1]) == 0)
				throw new IllegalArgumentException("Can not have duplicated points.");

		// Brute force.
		for (int i = 0; i < N; i++) {
			for (int ii = i + 1; ii < N; ii++) {
				for (int iii = ii + 1; iii < N; iii++) {
					for (int iv = iii + 1; iv < N; iv++) {
						Point one = fourPointsArray[i];
						Point two = fourPointsArray[ii];
						Point three = fourPointsArray[iii];
						Point four = fourPointsArray[iv];

						// If the slope is the same on the 4 points we add the segment to the array
						if (one.slopeTo(two) == two.slopeTo(three) && two.slopeTo(three) == three.slopeTo(four)) {
							segments.add(new LineSegment(one, four));
						}
					}
				}
			}
		}
	}

	public int numberOfSegments() {
		return segments.size();
	}

	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[numberOfSegments()]);
	}

	public static void main(String[] args) {
	}
}
