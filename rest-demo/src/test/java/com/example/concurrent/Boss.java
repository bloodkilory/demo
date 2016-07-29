package com.example.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author yangkun
 *         generate on 16/7/29
 */
public class Boss implements Runnable {

    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
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
        System.out.println("老板正在等待所有工人干完活...");
        try {
            this.countDownLatch.await();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工人活全都干完了,老板开始检查!");
    }
}
