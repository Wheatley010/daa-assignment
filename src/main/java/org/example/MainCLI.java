package org.example;

import java.util.Random;
import java.util.Arrays;

public class MainCLI {

    public static void main(String[] args) {
        int n = 1000;
        if (args.length > 0) {
            try { n = Integer.parseInt(args[0]); }
            catch (NumberFormatException e) { System.out.println("Invalid size, using 1000"); }
        }

        int[] array = new Random(123).ints(n, -1000, 1000).toArray();

        Metrics mergeMetrics = new Metrics();
        int[] arr = array.clone();
        MergeSort.sort(arr, mergeMetrics);
        mergeMetrics.stop();

        Metrics quickMetrics = new Metrics();
        arr = array.clone();
        QuickSort.sort(arr, quickMetrics);
        quickMetrics.stop();

        // Select
        Metrics selectMetrics = new Metrics();
        Integer[] arrBoxed = Arrays.stream(array).boxed().toArray(Integer[]::new);
        int medianIndex = arrBoxed.length / 2;
        int median = Select.mom5(arrBoxed, medianIndex);
        selectMetrics.stop();

        // Closest Pair
        Metrics closestMetrics = new Metrics();
        ClosestPair.Point[] points = new ClosestPair.Point[n];
        Random rnd = new Random(123);
        for (int i = 0; i < n; i++) points[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
        ClosestPair.findClosest(points, closestMetrics);
        closestMetrics.stop();

        // Вывод
        System.out.println("MergeSort time: " + mergeMetrics.elapsedTime());
        System.out.println("QuickSort time: " + quickMetrics.elapsedTime());
        System.out.println("Select median: " + median);
        System.out.println("ClosestPair time: " + closestMetrics.elapsedTime());

        String[] header = {"Algorithm","Time(ms)","MaxDepth","Comparisons"};
        String[][] data = {
                {"MergeSort", String.valueOf(mergeMetrics.elapsedTime()), String.valueOf(mergeMetrics.maxDepth()), String.valueOf(mergeMetrics.comparisons())},
                {"QuickSort", String.valueOf(quickMetrics.elapsedTime()), String.valueOf(quickMetrics.maxDepth()), String.valueOf(quickMetrics.comparisons())},
                {"Select", String.valueOf(selectMetrics.elapsedTime()), "n/a", "n/a"},
                {"ClosestPair", String.valueOf(closestMetrics.elapsedTime()), String.valueOf(closestMetrics.maxDepth()), String.valueOf(closestMetrics.comparisons())}
        };
        Metrics.writeCSV("metrics.csv", header, data);
        System.out.println("Metrics written to metrics.csv");
    }
}
