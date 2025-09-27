package org.example;

import java.util.Random;

public class SortUtils {
    private static final Random random=new Random();

    public static <T> void swap(T[] arr,int i,int j){
        T tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static <T> void shuffle(T[] arr){
        for(int i=arr.length-1;i>0;i--){
            int j=random.nextInt(i+1);
            swap(arr,i,j);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] arr,int low,int high){
        T pivot=arr[high];
        int i=low-1;
        for(int j=low;j<high;j++){
            if(arr[j].compareTo(pivot)<=0){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }

    public static <T> boolean guard(T[] arr){
        return arr==null||arr.length<=1;
    }
}
