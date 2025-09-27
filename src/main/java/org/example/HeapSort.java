package org.example;

public class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;
        for (int k = n / 2 - 1; k >= 0; k--) {
            sink(a, k, n);
        }
        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);
            sink(a, 0, end);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] a, int k, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && a[j].compareTo(a[j + 1]) < 0) j++;
            if (a[k].compareTo(a[j]) >= 0) break;
            swap(a, k, j);
            k = j;
        }
    }

    private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
