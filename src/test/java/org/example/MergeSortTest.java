package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest{
    @Test
    void randomTests(){
        Random rnd=new Random(123);
        int[] sizes={0,1,2,10,50,100,1000};
        for(int n:sizes){
            int[] a=rnd.ints(n,-1000,1000).toArray();
            int[] b=Arrays.copyOf(a,a.length);
            MergeSort.sort(a);
            Arrays.sort(b);
            assertArrayEquals(b,a);
        }
    }
}
