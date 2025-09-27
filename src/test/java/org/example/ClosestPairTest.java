// ClosestPairTest.java
package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    @Test
    void testSmallArray() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(2,2),
                new ClosestPair.Point(3,3)
        };
        Metrics metrics = new Metrics();
        double minDist = ClosestPair.findClosest(points, metrics);
        assertEquals(Math.sqrt(2), minDist, 1e-6);
    }

    @Test
    void testRandomArray() {
        int n = 100;
        ClosestPair.Point[] points = new ClosestPair.Point[n];
        Random rand = new Random(123);
        for (int i = 0; i < n; i++) points[i] = new ClosestPair.Point(rand.nextDouble()*100, rand.nextDouble()*100);

        Metrics metrics1 = new Metrics();
        double brute = ClosestPair.bruteForce(points, metrics1);
        Metrics metrics2 = new Metrics();
        double fast = ClosestPair.findClosest(points, metrics2);

        assertEquals(brute, fast, 1e-6);
    }
}
