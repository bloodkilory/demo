package com.example.demo.lock;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Maps;

/**
 * 描述：<br>
 * 版权：Copyright (c) 2011-2020<br>
 * 公司：活力天汇<br>
 * 作者：杨坤<br>
 * 版本：1.0<br>
 * 创建日期：2018/12/2<br>
 */
public class Cache {

    private static Map<String, Object> map = Maps.newHashMap();
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static Lock r = rwl.readLock();
    private static Lock w = rwl.writeLock();

    public static Object get(String key) {
        //读锁是一个支持重入的共享锁，能够被多个线程同时获取
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static Object put(String key, Object val) {
        //写锁被获取时,所有的其他读写线程均会被阻塞；
        w.lock();
        try {
            return map.put(key, val);
        } finally {
            w.unlock();
        }
    }
}
