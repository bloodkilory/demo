package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：<br>
 * 版权：Copyright (c) 2011-2020<br>
 * 公司：活力天汇<br>
 * 作者：杨坤<br>
 * 版本：1.0<br>
 * 创建日期：2020/2/27<br>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        // true 表示让LinkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部；
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //  map中数据量大于指定缓存个数时，自动删除最老的数据；
        return size() > CACHE_SIZE;
    }
}
