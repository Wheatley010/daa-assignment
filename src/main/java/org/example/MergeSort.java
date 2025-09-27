package org.example;

public class MergeSort{
    public static void sort(int[] arr){
        if(arr==null||arr.length<2)return;
        int[] aux=new int[arr.length];
        System.arraycopy(arr,0,aux,0,arr.length);
        sort(aux,arr,0,arr.length);
    }
    private static void sort(int[] src,int[] dst,int lo,int hi){
        if(hi-lo<=16){ins(dst,lo,hi);return;}
        int mid=(lo+hi)>>>1;
        sort(dst,src,lo,mid);
        sort(dst,src,mid,hi);
        int i=lo,j=mid,k=lo;
        while(i<mid&&j<hi){
            if(src[i]<=src[j])dst[k++]=src[i++]; else dst[k++]=src[j++];
        }
        while(i<mid)dst[k++]=src[i++];
        while(j<hi)dst[k++]=src[j++];
    }
    private static void ins(int[] a,int lo,int hi){
        for(int i=lo+1;i<hi;i++){
            int v=a[i],j=i-1;
            while(j>=lo&&a[j]>v){a[j+1]=a[j];j--;}
            a[j+1]=v;
        }
    }
}
