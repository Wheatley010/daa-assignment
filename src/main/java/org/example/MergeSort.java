package org.example;

public class MergeSort {
    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length < 2) return;
        int[] aux = new int[arr.length];
        System.arraycopy(arr, 0, aux, 0, arr.length);
        sort(aux, arr, 0, arr.length, metrics);
    }

    private static void sort(int[] src, int[] dst, int lo, int hi, Metrics metrics) {
        metrics.incDepth();

        if (hi - lo <= 16) {
            ins(dst, lo, hi, metrics);
            metrics.decDepth();
            return;
        }

        int mid = (lo + hi) >>> 1;
        sort(dst, src, lo, mid, metrics);
        sort(dst, src, mid, hi, metrics);

        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            metrics.addComparisons(1);
            if (src[i] <= src[j]) dst[k++] = src[i++];
            else dst[k++] = src[j++];
        }
        while (i < mid) dst[k++] = src[i++];
        while (j < hi) dst[k++] = src[j++];

        metrics.decDepth();
    }

    private static void ins(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i < hi; i++) {
            int v = a[i], j = i - 1;
            while (j >= lo) {
                metrics.addComparisons(1);
                if (a[j] > v) a[j + 1] = a[j--];
                else break;
            }
            a[j + 1] = v;
        }
    }
}
