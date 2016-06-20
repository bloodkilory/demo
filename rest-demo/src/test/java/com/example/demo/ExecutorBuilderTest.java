package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.example.pojo.Emp;

/**
 * @author yangkun
 *         generate on 16/6/20
 */
public class ExecutorBuilderTest {
    private static List<Emp> emps = new ArrayList<>();
    private static Emp.Builder builder = Emp.builder();

    public static void main(String[] args) {
        // 使用单例构建器会产生线程安全问题


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        for(int i = 0; i < 10; i++) {
            final int j = i;
            executor.execute(() -> {
                Emp e = builder.id(String.valueOf(Thread.currentThread())).name("name-" + j).build();
                emps.add(e);
            });
        }

        emps.forEach(System.out::println);

    }

}
