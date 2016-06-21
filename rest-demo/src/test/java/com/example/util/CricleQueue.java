package com.example.util;

/**
 * 基于数组实现的循环队列,可以避免队列假溢出
 * 但是队列大小固定
 *
 * @author yangkun
 *         generate on 16/6/21
 */
public class CricleQueue<T> implements Queue<T> {

    private T[] data;
    private int front;
    private int rear;
    private static final int DEFAULT_SIZE = 10;

    public CricleQueue() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public CricleQueue(int size) {
        data = (T[]) new Object[size];
        front = 0;
        rear = 0;
    }

    @Override
    public void enqueue(T t) {

    }

    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public int size() {
        return 0;
    }

    public void resize() {

    }

    @Override
    public T front() {
        return null;
    }

    @Override
    public T dequeue() {
        return null;
    }
}
