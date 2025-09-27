package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTest {
    @Test
    void testSortIntegers() {
        Integer[] input = {4, 10, 3, 5, 1};
        Integer[] expected = {1, 3, 4, 5, 10};
        HeapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testSortStrings() {
        String[] input = {"pear", "apple", "orange"};
        String[] expected = {"apple", "orange", "pear"};
        HeapSort.sort(input);
        assertArrayEquals(expected, input);
    }
}
