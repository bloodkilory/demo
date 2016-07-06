package com.example.demo;

/**
 * @author yangkun
 *         generate on 16/6/21
 */
public class CommonDemo {
    public static void main(String[] args) {


        int j = 0;
        for(int i = 10; i <= 10000; i *= 10) {
            System.out.println(i);
            ++j;
        }
        System.out.println(j);
        System.out.println(Math.log10(10000));
    }
}
