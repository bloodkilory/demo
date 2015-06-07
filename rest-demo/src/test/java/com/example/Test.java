package com.example;

/**
 * @author bloodkilory
 *         generate on 15/5/28
 */
public class Test {
	public static void main(String[] args) {

		int[] arr = new int[101];
		for(int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		arr[100] = arr[10];
		Object a = new Object();


		int sum = 0;
		int n = 0;
		for(int i = 0; i <= 100; i++) {
			sum += arr[i];
		}
		for(int i = 1; i <= 100; i++) {
			n += i;
		}
		System.out.println(sum - n);
	}
}
