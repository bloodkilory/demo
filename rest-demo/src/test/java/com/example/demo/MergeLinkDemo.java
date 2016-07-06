package com.example.demo;

import com.example.util.LinkNode;

/**
 * @author yangkun
 *         generate on 16/6/19
 */
public class MergeLinkDemo {
    public static void main(String[] args) {
        LinkNode a = new LinkNode(1, new LinkNode(5, new LinkNode(7, null)));
        LinkNode b = new LinkNode(3, new LinkNode(6, new LinkNode(8, new LinkNode(9, null))));

        LinkNode merged = LinkNode.mergeLink(a, b);
        while(merged != null) {
            System.out.println(merged.getData());
            merged = merged.getNext();
        }
        System.out.println(LinkNode.getComplex());
    }
}
