package com.example.demo.singleton;

/**
 * 延迟加载单例模式
 * 基于类初始化的线程安全延迟加载
 *
 * @author yangkun
 *         generate on 16/6/7
 */
public class DemandHolderSingleton {

    /**
     * 当JVM在执行类的初始化过程中,会获取锁
     * 即使在执行1的时候发生了重排序，对于其他未获取到虚拟机初始化锁的线程是不可见的，其他线程只能获取到初始化完成的对象
     */
    private static class Inner {
        static DemandHolderSingleton singleton = new DemandHolderSingleton(); // 1
    }

    private DemandHolderSingleton() {
    }

    /**
     * 比volatile更简洁优雅的实现方案, 减少了访问的开销, 但仅限于静态字段
     * <p>
     * 初始化情况：
     * 1.T是一个类，且一个T类型的实例被创建；
     * 2.T是一个类，且T中被声明的一个静态方法被调用；
     * 3.T中声明的一个静态字段被赋值；
     * 4.T中声明的一个静态字段被只用，且该字段不是一个常量字段
     *
     * @return
     */
    public static DemandHolderSingleton getSingleton() {
        return Inner.singleton;
    }

}
