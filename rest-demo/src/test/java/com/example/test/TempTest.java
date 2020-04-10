package com.example.test;

import org.junit.Test;

/**
 * @author yangkun
 *         generate on 16/8/30
 */
public class TempTest {

    @Test
    public void test1() {
        String tt = "nnn<br/>aaa";
        System.out.println(tt.replaceAll("<br/>", "ccc"));

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
