package com.example.util;

/**
 * @author yangkun
 *         generate on 16/6/21
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node head;
    private Node rear;
    private int size;

    public LinkedQueue() {
        head = null;
        rear = null;
        size = 0;
    }

    private class Node {
        T data;
        Node next;

        Node() {
        }

        Node(T t) {
            this.data = t;
        }
    }

    @Override
    public void enqueue(T t) {
        Node newNode = new Node(t);
        if(isEmpty()) {
            this.head = newNode;
        } else {
            this.rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T front() {
        return null;
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            return null; // 下溢
        }
        if(head.next == null) { // 边界处理
            rear = null;
        }
        T tmp = this.head.data;
        head = head.next;
        size--;
        return tmp;
    }
}
