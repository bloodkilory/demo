package com.example.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yangkun
 *         generate on 16/7/29
 */
public class Worker implements Runnable {

    private CountDownLatch countDownLatch;

    private String name;

    public Worker(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.doWork();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + "活干完了!");
        this.countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println(this.name + "正在工作!");
    }
}
