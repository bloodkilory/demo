package com.example.test;


import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.example.util.SortUtil;

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
        System.out.println("原始数组: " + Arrays.toString(arr));
        SortUtil.quickSort(arr, 0, arr.length - 1);
        System.out.println("排序数组: " + Arrays.toString(arr));
    }

}
