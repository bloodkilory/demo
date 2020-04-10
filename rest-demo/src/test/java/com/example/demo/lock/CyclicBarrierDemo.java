package com.example.demo.lock;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.collect.Lists;

/**
 * 描述：<br>
 * 版权：Copyright (c) 2011-2020<br>
 * 公司：活力天汇<br>
 * 作者：杨坤<br>
 * 版本：1.0<br>
 * 创建日期：2018/12/2<br>
 */
public class CyclicBarrierDemo implements Runnable {

    //创建线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(4);
    //使用CyclicBarrier提供的更高级构造器，用于在线程到达屏障时，优先执行A线程。
    private CyclicBarrier c = new CyclicBarrier(4, this);
    private List<Integer> list = Lists.newCopyOnWriteArrayList();
    private AtomicBoolean atomBoo = new AtomicBoolean(false);

    public void count() {
        for(int i = 0; i < 4; i++) { //因为只有4个线程，所以这里只循环4次
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                    if(atomBoo.get()) {
                        throw new Exception("重新执行一次");
                    } else {
                        list.add(1); //执行一些业务逻辑
                    }
                    c.await(); //插入一个屏障
                } catch(Exception e) {
                    System.out.println("error!");
                    c.reset();
                } finally {
                    atomBoo.compareAndSet(false, true);
                }
            });
        }
    }

    @Override
    public void run() {
        System.out.println(list.size());
    }

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.count();
    }
}
