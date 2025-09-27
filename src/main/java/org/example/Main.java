package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 1000; // размер массива
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10000); // случайные числа
        }

        Metrics metrics = new Metrics();
        metrics.start();

        MergeSort.sort(arr, metrics);

        metrics.stop();
        metrics.writeCSV("metrics.csv", "MergeSort", n);

        System.out.println("Elapsed ms: " + metrics.getElapsedMillis());
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Max recursion depth: " + metrics.getRecursionDepth());
    }
}
