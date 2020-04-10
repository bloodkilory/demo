package com.example.util;

/**
 * @author yangkun
 * generate on 16/12/5
 */
public class Search {

    /**
     * 递归实现二分查找
     *
     * @param arr
     * @return
     */
    public static int binarySearch(int[] arr, int low, int high, int key) {
        if(low > high) return -1;
        int mid = (low + high) >> 1;
        if(key == arr[mid]) {
            return mid;
        } else if(key < arr[mid]) {
            return binarySearch(arr, low, mid - 1, key);
        } else {
            if(key > arr[mid]) return binarySearch(arr, mid + 1, high, key);
        }
        return -1;
    }
}
