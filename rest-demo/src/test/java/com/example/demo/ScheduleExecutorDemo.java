package com.example.demo;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * @author yangkun
 *         generate on 16/11/10
 */
public class ScheduleExecutorDemo {

    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    /**
     * 模拟接口
     */
    @Test
    public void test() throws Exception{
        exe(new Date().getTime());
        Thread.currentThread().join();
    }

    @Test
    public void test2() throws Exception {
        destory();
    }

    /**
     * 模拟主逻辑
     * @param orderNo
     */
    public void exe(long orderNo) {
        executorService.scheduleAtFixedRate(new Trace(orderNo), 0, 3, TimeUnit.SECONDS);
    }

    /**
     * 模拟存储数据
     * @param orderNo
     */
    public void save(long orderNo, String result) {
        System.out.println(String.format("Order:%d saved. Result:%s", orderNo, result));
    }

    private class Trace implements Runnable {

        long orderNo;

        public Trace() {
        }

        public Trace(long orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public void run() {
            String result = Thread.currentThread().toString();
            save(orderNo, result);
        }
    }

    public void destory() {
        executorService.shutdownNow();
        System.out.println("executor destoryed!");
    }
}
