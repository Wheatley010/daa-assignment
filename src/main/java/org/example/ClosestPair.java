package org.example;

import java.util.Arrays;

public class ClosestPair {

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double dist(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, (a, b) -> Double.compare(a.x, b.x));
        Arrays.sort(py, (a, b) -> Double.compare(a.y, b.y));
        return closestPairRec(px, py);
    }

    private static double closestPairRec(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            return bruteForce(px);
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        Point[] Qy = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] Ry = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closestPairRec(Qx, Qy);
        double dr = closestPairRec(Rx, Ry);
        double d = Math.min(dl, dr);

        return Math.min(d, stripClosest(py, midPoint, d));
    }

    private static double stripClosest(Point[] py, Point midPoint, double d) {
        Point[] strip = Arrays.stream(py)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                double dist = dist(strip[i], strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    public static double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = dist(points[i], points[j]);
                if (d < min) {
                    min = d;
                }
            }
        }
        return min;
    }
}
