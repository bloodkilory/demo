package com.example.test;

import org.junit.Test;

/**
 * @author yangkun
 *         generate on 16/8/30
 */
public class TempTest {

    @Test
    public void test1() {
        float a = 2.2154f;
        float b = 0.1f;
        float c = 4.513f;
        float e = 5f;
        float f = 5.8f;
        calc(a);
        calc(b);
        calc(c);
        calc(e);
        calc(f);
        System.out.println(System.currentTimeMillis());

    }

    private void calc(float num) {
        int round = Math.round(num);
        if(round < num) {
            num = round + 0.5f;
        } else if(round > num && round - num < 0.5f) {
            num = round;
        }
        System.out.println("Actual num:" + num);
    }
}
