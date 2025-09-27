package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int n = 10000;
        Random rnd = new Random();

        int[] arr1 = rnd.ints(n, 0, n).toArray();
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();

        // MergeSort
        Metrics mergeMetrics = new Metrics();
        long start = System.nanoTime();
        MergeSort.sort(arr1, mergeMetrics); // существующий public метод
        long end = System.nanoTime();
        mergeMetrics.setElapsedTime((end - start) / 1_000_000.0);
        mergeMetrics.writeCSV("mergesort_metrics.csv", mergeMetrics.getComparisons(), mergeMetrics.getMaxDepth());
        System.out.println("MergeSort done. Time(ms): " + mergeMetrics.getElapsedTime());

        // QuickSort
        Metrics quickMetrics = new Metrics();
        start = System.nanoTime();
        QuickSort.sort(arr2, quickMetrics); // public метод
        end = System.nanoTime();
        quickMetrics.setElapsedTime((end - start) / 1_000_000.0);
        quickMetrics.writeCSV("quicksort_metrics.csv", quickMetrics.getComparisons(), quickMetrics.getMaxDepth());
        System.out.println("QuickSort done. Time(ms): " + quickMetrics.getElapsedTime());

        // Select Median-of-Medians
        Metrics selectMetrics = new Metrics();
        int k = arr3.length / 2;
        Integer[] arr3Integer = Arrays.stream(arr3).boxed().toArray(Integer[]::new);
        start = System.nanoTime();
        int median = Select.mom5(arr3Integer, k);
        end = System.nanoTime();
        selectMetrics.setElapsedTime((end - start) / 1_000_000.0);
        selectMetrics.writeCSV("select_metrics.csv", selectMetrics.getComparisons(), selectMetrics.getMaxDepth());
        System.out.println("Select MoM done. Median: " + median + " Time(ms): " + selectMetrics.getElapsedTime());

        // Closest Pair
        int pointsCount = 5000;
        ClosestPair.Point[] points = new ClosestPair.Point[pointsCount];
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new ClosestPair.Point(rnd.nextDouble() * 10000, rnd.nextDouble() * 10000);
        }
        Metrics cpMetrics = new Metrics();
        start = System.nanoTime();
        ClosestPair.findClosest(points, cpMetrics);
        end = System.nanoTime();
        cpMetrics.setElapsedTime((end - start) / 1_000_000.0);
        cpMetrics.writeCSV("closest_pair_metrics.csv", cpMetrics.getComparisons(), cpMetrics.getMaxDepth());
        System.out.println("ClosestPair done. Time(ms): " + cpMetrics.getElapsedTime());
    }
}
