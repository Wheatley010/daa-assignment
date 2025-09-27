package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private long startTime;
    private long elapsed;
    private int maxDepth;
    private int comparisons;

    public Metrics() {
        startTime = System.nanoTime();
        elapsed = 0;
        maxDepth = 0;
        comparisons = 0;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) maxDepth = depth;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void stop() {
        elapsed = System.nanoTime() - startTime;
    }

    public long elapsedTime() {
        return elapsed / 1_000_000; // в миллисекундах
    }

    public int maxDepth() { return maxDepth; }
    public int comparisons() { return comparisons; }

    public static void writeCSV(String filename, String[] header, String[][] data) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.append(String.join(",", header)).append("\n");
            for (String[] row : data) {
                fw.append(String.join(",", row)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
