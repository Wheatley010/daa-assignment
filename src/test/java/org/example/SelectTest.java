package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {
    @Test
    void testSelectMedian() {
        Integer[] arr = {7, 2, 9, 4, 1, 5};
        Integer result = Select.mom5(arr, arr.length / 2);
        assertEquals(5, result);
    }

    @Test
    void testSelectSmallest() {
        Integer[] arr = {10, 3, 8, 6};
        Integer result = Select.mom5(arr, 0);
        assertEquals(3, result);
    }
}
