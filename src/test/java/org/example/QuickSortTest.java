package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {
    @Test
    void testSortIntegers() {
        Integer[] input = {5, 3, 8, 4, 2};
        Integer[] expected = {2, 3, 4, 5, 8};
        QuickSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStrings() {
        String[] input = {"banana", "apple", "cherry"};
        String[] expected = {"apple", "banana", "cherry"};
        QuickSort.sort(input);
        assertArrayEquals(expected, input);
    }
}
