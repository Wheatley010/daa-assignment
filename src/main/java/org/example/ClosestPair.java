package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Point[] py = points.clone();
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closestPairRec(px, py, 0, points.length);
    }

    private static double closestPairRec(Point[] px, Point[] py, int left, int right) {
        int n = right - left;
        if (n <= 3) return bruteForce(px, left, right);

        int mid = left + n / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closestPairRec(px, pyl, left, mid);
        double dr = closestPairRec(px, pyr, mid, right);
        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);
        double minStrip = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                double dist = distance(strip[i], strip[j]);
                if (dist < minStrip) minStrip = dist;
            }
        }

        return Math.min(d, minStrip);
    }

    public static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j < right; j++) {
                double d = distance(points[i], points[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }
}
