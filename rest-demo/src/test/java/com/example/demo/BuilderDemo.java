package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.pojo.Emp;

/**
 * **验证单例构建器的线程安全问题**
 *
 * @author yangkun
 *         generate on 16/6/20
 */
public class BuilderDemo {

    private static List<Emp> emps = new ArrayList<>();

    public static void main(String[] args) {
        // 使用单例构建器会产生线程安全问题
        Emp.Builder builder = Emp.builder();

        List<Thread> threads = new ArrayList<>();
        Emp emp = builder.id(String.valueOf(100)).name("name" + 100).build();

        for(int i = 0; i < 10; i++) {
            final int j = i;
            Thread t = new Thread(() -> {

                Emp e = builder.id(String.valueOf(j)).name("name" + j).build();
                emps.add(e);
            });
            threads.add(t);

        }
        threads.forEach(Thread::start);

        for(Thread t : threads) {
            try {
                t.join();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        emps.forEach(System.out::println);
        System.out.println(emp);

    }
}
