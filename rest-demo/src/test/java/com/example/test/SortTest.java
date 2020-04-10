package com.example.test;


import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * Created by bloodkilory on 15/4/8.
 */
public class SortTest {

    @Test
    public void testQuickSort() {
        Random random = new Random();
        int arraysize = 20;
        int[] arr = new int[arraysize];

//		foreach循环不能为数组赋值！！！
//		for(int a : arr) {
//			a = random.nextInt(100);
//		}

        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println("origin: " + Arrays.toString(arr));
//        SortUtil.quickSort(arr, 0, arr.length - 1);
        sort(arr, 0, arr.length - 1);
        System.out.println("sorted: " + Arrays.toString(arr));
    }

    public static void sort(int[] arr, int low, int high) {
        if(low > high) {
            return;
        }
        int i = low;
        int j = high;
        int p = arr[low];
        while(i != j) {
            while(i < j && arr[j] >= p) {
                j--;
            }
            while(i < j && arr[i] <= p) {
                i++;
            }
            if(i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[low] = arr[i];
        arr[i] = p;
        sort(arr, low, i - 1);
        sort(arr, i + 1, high);
    }

}
