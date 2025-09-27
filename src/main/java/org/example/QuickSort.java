package org.example;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr){
        if(SortUtils.guard(arr)) return;
        SortUtils.shuffle(arr);
        quickSort(arr,0,arr.length-1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr,int low,int high){
        if(low<high){
            int pi=SortUtils.partition(arr,low,high);
            if(pi-low<high-pi){
                quickSort(arr,low,pi-1);
                quickSort(arr,pi+1,high);
            }else{
                quickSort(arr,pi+1,high);
                quickSort(arr,low,pi-1);
            }
        }
    }
}
