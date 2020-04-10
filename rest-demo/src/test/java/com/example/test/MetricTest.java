package com.example.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.example.util.HttpUtil;

/**
 * @author yangkun
 * generate on 2017/12/30
 */
public class MetricTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(50);

    @Test
    public void test() throws InterruptedException {
        final String url = "http://localhost:8150/metric/";

        while(true) {
            for(int i = 0; i < 50; i++) {
                executorService.submit(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        HttpUtil.doGet(url + "test");
                    } catch(Exception ex) {

                    }
                });
            }

            for(int i = 0; i < 50; i++) {
                executorService.submit(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        HttpUtil.doGet(url + "test2");
                    } catch(Exception ex) {

                    }
                });
            }
        }

    }
}
