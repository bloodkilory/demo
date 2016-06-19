package com.example.demo;

/**
 * 延迟加载单例模式
 * 基于类初始化的线程安全延迟加载
 *
 * @author yangkun
 *         generate on 16/6/7
 */
public class DemandHolderSingleton {

    /**
     * 当JVM在执行类的初始化过程中,会获取锁,此时对其他非执行初始化线程不可见
     */
    private static class Inner {
        public static DemandHolderSingleton singleton = new DemandHolderSingleton();
    }

    private DemandHolderSingleton() {
    }

    /**
     * 比volatile更简洁优雅的实现方案, 减少了访问的开销, 但仅限于静态字段
     *
     * @return
     */
    public static DemandHolderSingleton getSingleton() {
        return Inner.singleton;
    }

}
