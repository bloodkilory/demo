package com.example.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.pojo.Emp;

/**
 * @author yangkun
 *         generate on 16/4/22
 */
public class ForJavap {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("aaa", "bbb", "cccc");

        Emp e = Emp.builder(UUID.randomUUID().toString(), "zhangsan").age(12)
                .job("worker").sex("male").salary(new BigDecimal("2000")).build();


    }
}
