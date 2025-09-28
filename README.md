# Algorithms with Divide and Conquer

This project is my assignment for the DAA (Design and Analysis of Algorithms) course.
I implemented and tested several divide-and-conquer algorithms:

* MergeSort
* QuickSort
* Median of Medians (Selection)
* Closest Pair of Points

The program also collects metrics and allows me to compare the results with theory.

---

## Architecture Notes

I added a **metrics system** to measure how the algorithms behave.

* For recursion depth, I use a counter that increases when the algorithm goes deeper and decreases when it returns.
* For memory allocations:

    * MergeSort and Closest Pair create new subarrays. This makes coding easier but uses more memory.
    * QuickSort works in-place, so it saves memory.
    * Median of Medians uses only constant extra space.
* For performance, I measure:

    * running time with `System.nanoTime()`,
    * number of comparisons,
    * maximum recursion depth.

The results are stored in `.csv` files so I can plot them later.

---

## Recurrence Analysis

Here is my short analysis for each algorithm:

* **MergeSort:**
  Recurrence: T(n) = 2T(n/2) + Θ(n).
  By Master Theorem: T(n) = Θ(n log n).
  Recursion depth grows as log₂n.

* **QuickSort (randomized pivot):**
  Recurrence: T(n) = T(k) + T(n−k−1) + Θ(n).
  Average case: Θ(n log n).
  Worst case: Θ(n²).
  Depth is log₂n on average, but can go up to n.

* **Median of Medians (Selection):**
  Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n).
  By Akra–Bazzi: T(n) = Θ(n).
  Depth is proportional to log n.

* **Closest Pair of Points:**
  Recurrence: T(n) = 2T(n/2) + Θ(n log n).
  Result: T(n) = Θ(n log²n).
  Depth is log₂n.

---

## Experimental Results

I tested all algorithms with different input sizes.

* **Time vs n:**

    * MergeSort and QuickSort (average) matched the expected n log n behavior.
    * Median of Medians was slower because of bigger constant factors, but still linear.
    * Closest Pair grew faster, close to n log²n.

* **Depth vs n:**

    * MergeSort and Closest Pair stayed close to log₂n.
    * QuickSort sometimes went deeper when pivots were unlucky.

I also noticed that constant factors matter:

* MergeSort had extra overhead because of array allocations.
* QuickSort sometimes slowed down because of pivot choices.
* Closest Pair was slower for small n because of setup costs.

---

## Summary

In general, my experiments confirmed the theoretical analysis:

* MergeSort and QuickSort (on average) are Θ(n log n).
* Median of Medians is Θ(n), but constants make it slower.
* Closest Pair fits Θ(n log²n).

The main differences come from constant factors like cache, garbage collection, and memory allocations.

I think this shows how theoretical results and practical performance are connected but not always identical.
