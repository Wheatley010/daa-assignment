package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {
    @Test
    void testSortIntegers() {
        int[] input = {5, 3, 8, 4, 2};
        int[] expected = {2, 3, 4, 5, 8};
        Metrics metrics = new Metrics();
        QuickSort.sort(input, metrics); // передаём Metrics
        assertArrayEquals(expected, input);
    }

    @Test
    void testRandomIntegers() {
        int[] input = {10, -1, 7, 3, 5};
        int[] expected = {-1, 3, 5, 7, 10};
        Metrics metrics = new Metrics();
        QuickSort.sort(input, metrics);
        assertArrayEquals(expected, input);
    }
}
