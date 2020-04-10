package com.example.demo.lock;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

/**
 * 描述：<br>
 * 版权：Copyright (c) 2011-2020<br>
 * 公司：活力天汇<br>
 * 作者：杨坤<br>
 * 版本：1.0<br>
 * 创建日期：2018/12/2<br>
 */
public class CountDownLatchDemo {

    //创建线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    public void countDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        //使用CopyOnWriteList保证线程安全
        final List<Integer> list = Lists.newCopyOnWriteArrayList();
        for(int i = 0; i < 100; i++) { //循环100次
            executor.submit(() -> {
                try {
                    list.add(1);
                } finally {
                    latch.countDown(); //每次减1
                }
            });
        }
        //主线程会被阻塞，直到latch减致0或者超时抛出中断异常
        latch.await(3, TimeUnit.SECONDS);
        System.out.println(list.size()); //最终输出的结果一定是100
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        demo.countDownLatch();
    }

}
