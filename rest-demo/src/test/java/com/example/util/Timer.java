package com.example.util;

import java.util.concurrent.TimeUnit;

/**
 * 该类可作为统计方法运行时长的工具类使用于AOP中
 *
 * @author yangkun
 *         generate on 16/7/6
 */
public final class Timer {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    private Timer() {
    }

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Timer.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Timer.end() + " mills.");
    }
}
