package com.example.util;

/**
 * @author yangkun
 *         generate on 16/6/21
 */
public interface Queue<T> {
    void enqueue(T t);

    int size();

    boolean isEmpty();

    T front();

    T dequeue();
}
