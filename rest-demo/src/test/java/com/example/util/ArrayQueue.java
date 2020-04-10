package com.example.util;

/**
 * 基于数组实现的队列
 * 这种队列会产生队列假上溢现象
 *
 * @author yangkun
 *         generate on 16/6/21
 */
@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements Queue<T> {

    private T[] data;
    private int size;
    private int front;
    private int rear;

    public ArrayQueue() {
        this.data = (T[]) new Object[16];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    @Override
    public void enqueue(T t) {
        if(isFull()) {
            resize(); // 通过resize()方法解决真上溢
            front = 0;
        }
        rear = (front + size) % data.length;
        data[rear] = t;
        size++;
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        T temp = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return temp;
    }

    public boolean isFull() {
        return this.size == data.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void resize() {
        T[] tmp = (T[]) new Object[data.length << 1];

        if(front < rear) {
            //直接拷贝data[front..rear]到tmp[0..rear-front]
            System.arraycopy(data, front, tmp, 0, rear - front + 1);
        } else {
            //先拷贝后半截data[front..data.length()-1]到tmp[0..data.length()-1-front]
            System.arraycopy(data, front, tmp, 0, data.length - front);
            //再拷贝前半截data[0..front-1]到tmp[data.length()-front..data.length()-1]

            //注意rear=front-1
            System.arraycopy(data, 0, tmp, data.length - front, front);
        }
        data = tmp;
    }

    @Override
    public T front() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return data[front];
    }

}
