package org.example;

import java.util.Random;

public class QuickSort {

    private static final Random rnd = new Random(123);

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length < 2) return;
        quicksort(arr, 0, arr.length - 1, metrics, 0);
    }

    private static void quicksort(int[] arr, int left, int right, Metrics metrics, int depth) {
        metrics.updateDepth(depth);
        if (left >= right) return;

        int pivotIndex = left + rnd.nextInt(right - left + 1);
        pivotIndex = partition(arr, left, right, pivotIndex, metrics);

        int leftSize = pivotIndex - left;
        int rightSize = right - pivotIndex;

        if (leftSize < rightSize) {
            quicksort(arr, left, pivotIndex - 1, metrics, depth + 1);
            quicksort(arr, pivotIndex + 1, right, metrics, depth + 1);
        } else {
            quicksort(arr, pivotIndex + 1, right, metrics, depth + 1);
            quicksort(arr, left, pivotIndex - 1, metrics, depth + 1);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivot) swap(arr, storeIndex++, i);
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
