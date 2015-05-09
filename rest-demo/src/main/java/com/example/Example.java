package com.example;

import java.util.ArrayList;

/**
 * Created by bloodkilory on 15/3/14.
 */
public class Example {
	public static void main(String[] args) {

		ArrayList<Integer> arr = new ArrayList<>(100000001);
		for(int i = 0; i < 100000000; i++) {
			arr.add(i);
		}

		Long s1 = System.currentTimeMillis();
		for(int i : arr) {
			int a = 0;
			a += i;
		}
		Long e1 = System.currentTimeMillis();
		String time1 = (e1 - s1) / 1000D + "s";

		Long s2 = System.currentTimeMillis();
		for(int i = 0; i < arr.size(); i++) {
			int b = 0;
			b += arr.get(i);
		}
		Long e2 = System.currentTimeMillis();
		String time2 = (e2 - s2) / 1000D + "s";
		System.out.println(time1);
		System.out.println(time2);

	}
}
