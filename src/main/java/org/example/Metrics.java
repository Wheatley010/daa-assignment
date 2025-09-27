package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private long startTime;
    private long endTime;
    private int recursionDepth = 0;
    private int maxDepth = 0;
    private int comparisons = 0;

    public void start() {
        startTime = System.nanoTime();
        recursionDepth = 0;
        maxDepth = 0;
        comparisons = 0;
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public double getElapsedMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void incDepth() {
        recursionDepth++;
        if (recursionDepth > maxDepth) {
            maxDepth = recursionDepth;
        }
    }

    public void decDepth() {
        recursionDepth--;
    }

    public int getRecursionDepth() {
        return recursionDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void addComparisons(int count) {
        comparisons += count;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void writeCSV(String filename, String algorithm, int n) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(algorithm + "," + n + "," + getElapsedMillis() + "," + getMaxDepth() + "," + getComparisons() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
