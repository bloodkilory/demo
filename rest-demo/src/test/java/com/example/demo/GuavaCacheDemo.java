package com.example.demo;


import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.*;

/**
 * @author yangkun
 *         generate on 16/7/19
 */
public class GuavaCacheDemo {

    private LoadingCache<Integer, String> cache = CacheBuilder
            .newBuilder()
            .maximumSize(10)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer integer) throws Exception {
                    return "In the Cache: " + integer;
                }
            });

    private Cache<Integer, String> cache2 = CacheBuilder
            .newBuilder()
            .maximumSize(10)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    @Test
    public void test() throws Exception {
        System.out.println(cache2.getIfPresent(2));
        cache2.put(2, "2ca");
        System.out.println(cache2.getIfPresent(2));
        System.out.println(cache2.getIfPresent(1));
        System.out.println(cache2.getIfPresent(1));
        System.out.println(cache2.getIfPresent(4));
    }

}



