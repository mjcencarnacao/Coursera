import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	/*
	 * A faster, sorting-based solution. Remarkably, it is possible to solve the
	 * problem much faster than the brute-force solution. Given a
	 * point p, the following method determines whether p participates in a set of 4
	 * or more collinear points.
	 * 
	 * Think of p as the origin.
	 * 
	 * For each other point q, determine the slope it makes with p.
	 * 
	 * Sort the points according to the slopes they makes with p.
	 * 
	 * Check if any 3 (or more) adjacent points in the sorted order have equal
	 * slopes with respect to p. If so, these points, together with p, are
	 * collinear.
	 * 
	 * Applying this method for each of the n points in turn yields an efficient
	 * algorithm to the problem. The algorithm solves the problem because points
	 * that have equal slopes with respect to p are collinear, and sorting brings
	 * such points together. The algorithm is fast because the bottleneck operation
	 * is sorting.
	 */

	private final Point[] pointsArray;
	private final ArrayList<LineSegment> segments;

	public FastCollinearPoints(Point[] pointArray) {
		if (pointArray == null)
			throw new IllegalArgumentException("The array can not be null.");
		for (Point x : pointArray)
			if (x == null)
				throw new IllegalArgumentException("There can not be null points.");

		pointsArray = pointArray.clone();
		segments = new ArrayList<>();
		Arrays.sort(pointsArray);
		for (int i = 0; i < pointsArray.length - 1; i++)
			if (pointsArray[i].compareTo(pointsArray[i + 1]) == 0)
				throw new IllegalArgumentException("Can not have duplicated points.");
		for (Point x : pointsArray) {
			Point[] points = pointsArray.clone();
			Arrays.sort(points, x.slopeOrder());
			double previousSlopeAux = Double.NaN;
			LinkedList<Point> segment = new LinkedList<>();

			for (int index = 0; index < points.length; index++) {

				if (x.slopeTo(points[index]) != previousSlopeAux) {

					if (segment.size() > 2 && x.compareTo(segment.peekFirst()) < 0)
						segments.add(new LineSegment(x, segment.peekLast()));
					segment.clear();
				}
				segment.add(points[index]);
				previousSlopeAux = x.slopeTo(points[index]);
			}
		}
	}

	public int numberOfSegments() {
		return segments.size();
	}

	/*
	 * The method segments() should include each maximal line segment containing 4
	 * (or more) points exactly once. For example, if 5 points appear on a line
	 * segment in the order p→q→r→s→t, then do not include the subsegments p→s or
	 * q→t.
	 */

	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[numberOfSegments()]);
	}

	public static void main(String[] args) {
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}

}