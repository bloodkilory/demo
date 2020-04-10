package com.example.demo.singleton;

/**
 * 延迟加载单例模式
 * 基于volatile的线程安全双重检查锁定
 *
 * @author yangkun
 *         generate on 16/6/7
 */
public class DoubleCheckSingleton {

    // 如果不指定为volatile, 编译器指令重排序可能导致问题
    // 可以实现对实例字段的延迟初始化
    private volatile static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getSingleton() {
        if(singleton == null) { // 第一次检查，如果不为空，可不执行同步块，减少性能开销
            synchronized(DoubleCheckSingleton.class) {
                if(singleton == null) { // 第二次检查
                    singleton = new DoubleCheckSingleton(); // 1 非voletile延迟加载的问题出在这里！
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
/// instance = memory;     // 3 设置对象指向分配的内存空间（如果这一步重排序到了2之前，那么线程B在访问对象时，会判断对象不为null
// 从而直接使用这个对象，但此时，线程A还未完成步骤2，对象并未被正确的初始化，在多线程环境下，volatile将禁止2、3之间的重排序）

