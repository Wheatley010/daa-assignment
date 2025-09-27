package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private int comparisons = 0;
    private int maxDepth = 0;
    private double elapsedTime = 0;

    public void addComparisons(int c) { comparisons += c; }
    public void updateDepth(int depth) { if(depth > maxDepth) maxDepth = depth; }

    public int getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }

    public void setElapsedTime(double ms) { this.elapsedTime = ms; }
    public double getElapsedTime() { return elapsedTime; }

    public void writeCSV(String filename, int comparisons, int maxDepth) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("comparisons,maxDepth\n");
            fw.write(comparisons + "," + maxDepth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
