package org.example;

import java.util.Arrays;

public class ClosestPair {

    public static class Point {
        double x, y;
        public Point(double x, double y){ this.x = x; this.y = y; }
    }

    public static void findClosest(Point[] points, Metrics metrics){
        Point[] px = points.clone();
        Arrays.sort(px, (a,b)->Double.compare(a.x, b.x));
        Point[] py = points.clone();
        Arrays.sort(py, (a,b)->Double.compare(a.y, b.y));
        closest(px, py, metrics, 1);
    }

    private static double closest(Point[] px, Point[] py, Metrics metrics, int depth){
        metrics.updateDepth(depth);
        int n = px.length;
        if(n <= 3){
            double min = Double.MAX_VALUE;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    metrics.addComparisons(1);
                    double d = dist(px[i], px[j]);
                    if(d<min) min=d;
                }
            }
            return min;
        }
        int mid = n/2;
        Point midPoint = px[mid];
        final Point midPt = midPoint; // фикс для lambda

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        Point[] Qy = Arrays.stream(py).filter(p -> p.x <= midPt.x).toArray(Point[]::new);
        Point[] Ry = Arrays.stream(py).filter(p -> p.x > midPt.x).toArray(Point[]::new);

        double dl = closest(Qx, Qy, metrics, depth+1);
        double dr = closest(Rx, Ry, metrics, depth+1);
        double d = Math.min(dl, dr);

        // Создаём strip через обычный цикл, чтобы d не использовалось в lambda
        int count = 0;
        Point[] stripTemp = new Point[py.length];
        for(Point p : py){
            if(Math.abs(p.x - midPt.x) < d){
                stripTemp[count++] = p;
            }
        }
        Point[] strip = Arrays.copyOf(stripTemp, count);

        for(int i=0;i<strip.length;i++){
            for(int j=i+1;j<Math.min(i+7, strip.length);j++){
                metrics.addComparisons(1);
                double dist = dist(strip[i], strip[j]);
                if(dist < d) d=dist;
            }
        }
        return d;
    }

    private static double dist(Point a, Point b){
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
