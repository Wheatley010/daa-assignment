package org.example;

import java.util.Arrays;

public class Select {
    public static <T extends Comparable<T>> T mom5(T[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static <T extends Comparable<T>> T select(T[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = pivot(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex, k);
        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return select(arr, left, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, right, k);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right, int pivotIndex, int k) {
        T pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i].compareTo(pivotValue) < 0) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static <T extends Comparable<T>> int pivot(T[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }
        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(arr, i, subEnd + 1);
            int median = (i + subEnd) / 2;
            swap(arr, median, subRight);
            subRight++;
        }
        return pivot(arr, left, subRight - 1);
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
