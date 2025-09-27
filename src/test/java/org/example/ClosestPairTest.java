package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void testSmallArray() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(2,2),
                new ClosestPair.Point(3,3)
        };
        double minDist = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), minDist, 1e-6);
    }

    @Test
    void testRandomArray() {
        int n = 100;
        ClosestPair.Point[] points = new ClosestPair.Point[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            points[i] = new ClosestPair.Point(rand.nextDouble()*100, rand.nextDouble()*100);
        }

        double brute = ClosestPair.bruteForce(points);
        double fast = ClosestPair.closestPair(points);

        assertEquals(brute, fast, 1e-6);
    }
}
