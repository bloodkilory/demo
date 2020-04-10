package com.example.util;

/**
 * @author bloodkilory
 * generate on 15/5/25
 */
public final class SortUtil {

	private SortUtil() {
	}

	/**
	 * 快速排序
	 * 时间复杂度 O(nlogn)
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] array, int low, int high) {
		if(low > high) return; // 递归结束条件
		int i = low;
		int j = high;
		int pivot = array[low];
		while(i != j) {
			while(i < j && array[j] >= pivot) {
				j--;
			}
			while(i < j && array[i] <= pivot) {
				i++;
			}
			if(i < j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		array[low] = array[i];
		array[i] = pivot;

		quickSort(array, low, i - 1);
		quickSort(array, i + 1, high);
	}

	public static void popupSort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length - 1 - i; j++) {
				if(array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
}
