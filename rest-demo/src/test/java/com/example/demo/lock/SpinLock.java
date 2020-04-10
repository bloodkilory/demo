package com.example.demo.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述：<br>
 * 版权：Copyright (c) 2011-2020<br>
 * 公司：活力天汇<br>
 * 作者：杨坤<br>
 * 版本：1.0<br>
 * 创建日期：2018/12/2<br>
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        //获取当前线程对象
        Thread current = Thread.currentThread();
        //当sign持有线程不为空时，循环等待
        while(!sign.compareAndSet(null, current)) {
            //当sign持有线程为空时，将sign持有线程设为当前线程，退出循环
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        //执行完成后，将sign持有的线程置空
        sign.compareAndSet(current, null);
    }
}
