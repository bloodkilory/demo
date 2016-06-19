package com.example.demo;

/**
 * 延迟加载单例模式
 * 基于volatile的线程安全双重检查锁定
 *
 * @author yangkun
 *         generate on 16/6/7
 */
public class DoubleCheckSingleton {

    // 如果不指定为volatile,则在1时,有可能变量未初始化完成导致其他线程获取到的仍旧为null
    // 可以实现对实例字段的延迟初始化
    private volatile static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getSingleton() {
        if(singleton == null) {
            synchronized(DoubleCheckSingleton.class) {
                if(singleton == null) {
                    singleton = new DoubleCheckSingleton(); // 1
                }
            }
        }
        return singleton;
    }
}
/**
 * 2与3这两步的重排序会导致变量未初始化完成就被其他线程访问
 */
/// memory = allocate();   // 分配内存
/// ctorInstance(memory);  // 2 初始化对象
/// instance = memory;     // 3 设置对象指向分配的内存空间

