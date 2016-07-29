package com.example.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch demo
 * 老板等所有工人做完工作后检查工作,
 * 利用countdownlatch来检查工人做完工作
 *
 * @author yangkun
 *         generate on 16/7/29
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Worker w1 = new Worker(latch, "张三");
        Worker w2 = new Worker(latch, "李四");
        Worker w3 = new Worker(latch, "王五");

        Boss boss = new Boss(latch);

        executorService.execute(w1);
        executorService.execute(w2);
        executorService.execute(w3);
        executorService.execute(boss);

        executorService.shutdown();
    }
}
