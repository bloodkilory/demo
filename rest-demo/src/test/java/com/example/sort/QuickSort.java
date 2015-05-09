package com.example.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * Created by bloodkilory on 15/4/8.
 */
public class QuickSort {
	public static void main(String[] args) {
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
		sort(arr, 0, arraysize - 1);
		System.out.println("排序数组: " + Arrays.toString(arr));
	}

	public static void sort(int[] array, int low, int high) {
		//  递归结束条件
		if(low > high) {
			return;
		}
		int i = low;    // 哨兵1
		int j = high;   // 哨兵2
		int temp;  // 临时变量交换用
		int pivot = array[low];  // 标志位/基准点
		while(i != j) {
			while(i < j && array[j] >= pivot) {
				j--;
			}
			while(i < j && array[i] <= pivot) {
				i++;
			}
			if(i < j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		// 将基准数归位
		array[low] = array[i];
		array[i] = pivot;

		// 递归
		sort(array, low, i - 1);
		sort(array, i + 1, high);
	}

}
