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
            .maximumSize(200)
            .weakKeys()
            .softValues()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Integer, String>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, String> rn) {
                    System.out.println("移除: " + rn.getKey());

                }
            })
            .build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer integer) throws Exception {
                    return "加载: " + integer;
                }
            });

    @Test
    public void test() throws Exception {
        System.out.println(cache.get(123));
        System.out.println(cache.getUnchecked(234));
        Thread.sleep(4000);

//        cache.put(333, "Hello");
//        cache.put(555, "xx");
        System.out.println(cache.get(234));
//        System.out.println(cache.size());
    }

}



