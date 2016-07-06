package com.example.util;

/**
 * 简单链表
 *
 * @author yangkun
 *         generate on 16/6/19
 */
public class LinkNode {
    private int data;
    private LinkNode next;
    private static int complex = 0;

    public LinkNode() {
    }

    public LinkNode(int data, LinkNode next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public static LinkNode mergeLink(LinkNode a, LinkNode b) {
        complex++;
        if(a == null) {
            return b;
        }
        if(b == null) {
            return a;
        }
        LinkNode merged;
        if(a.data <= b.data) {
            merged = a;
            merged.next = mergeLink(a.next, b);
        } else {
            merged = b;
            merged.next = mergeLink(a, b.next);
        }
        return merged;
    }

    public static int getComplex() {
        return complex;
    }
}
