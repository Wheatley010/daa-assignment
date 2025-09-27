// ClosestPair.java
package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double findClosest(Point[] points, Metrics metrics) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closest(px, py, metrics);
    }

    private static double closest(Point[] px, Point[] py, Metrics metrics) {
        int n = px.length;
        if (n <= 3) return bruteForce(px, metrics);
        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.copyOfRange(py, 0, mid);
        Point[] pyr = Arrays.copyOfRange(py, mid, n);

        double dl = closest(Arrays.copyOfRange(px, 0, mid), pyl, metrics);
        double dr = closest(Arrays.copyOfRange(px, mid, n), pyr, metrics);

        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);
        double minDist = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < Math.min(i + 8, strip.length); j++) {
                metrics.incrementComparisons();
                double dist = distance(strip[i], strip[j]);
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }

    public static double bruteForce(Point[] points, Metrics metrics) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {
                metrics.incrementComparisons();
                double dist = distance(points[i], points[j]);
                if (dist < min) min = dist;
            }
        return min;
    }
}
