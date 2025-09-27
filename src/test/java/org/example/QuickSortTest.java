package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.Arrays;

class QuickSortTest {
    @Test
    void testSortIntegers() {
        int[] input = {5,3,8,4,2};
        int[] expected = {2,3,4,5,8};
        Metrics metrics = new Metrics();
        QuickSort.sort(input, metrics);
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortRandom() {
        int[] input = {10, -1, 7, 4};
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        Metrics metrics = new Metrics();
        QuickSort.sort(input, metrics);
        assertArrayEquals(expected, input);
    }
}
