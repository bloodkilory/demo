package com.example.test;

import java.util.Arrays;

import org.junit.Test;

import com.example.util.ArrayQueue;
import com.example.util.BinaryTree;
import com.example.util.LinkedQueue;

/**
 * @author yangkun
 *         generate on 16/6/21
 */
public class DataStructureTest {
    @Test
    public void testBinaryTree() {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        intTree.add(1);
        intTree.add(12);
        intTree.add(7);
        intTree.add(3);
        intTree.add(5);
        intTree.add(4);
        intTree.add(21);
        intTree.add(15);
        Object[] result = intTree.toArray();
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testArrayQueue() {
        ArrayQueue<String> aq = new ArrayQueue<>();
        aq.enqueue("aa");
        aq.enqueue("bb");
        aq.enqueue("cc");
        aq.enqueue("dd");
        System.out.println(aq.dequeue());
        System.out.println(aq.dequeue());
        System.out.println(aq.dequeue());
        System.out.println(aq.dequeue());
    }

    @Test
    public void testLinkedQueue() {
        LinkedQueue<String> lq = new LinkedQueue<>();
        lq.enqueue("aa");
        lq.enqueue("cc");
        lq.enqueue("bb");
        lq.enqueue("zz");
        lq.enqueue("nn");
        lq.enqueue("mm");
        lq.enqueue("ss");
        lq.enqueue("gg");
        lq.enqueue("oo");
        lq.enqueue("pp");
        lq.enqueue("ll");
        lq.enqueue("ii");
        lq.enqueue("qq");
        lq.enqueue("ww");
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());

    }
}
