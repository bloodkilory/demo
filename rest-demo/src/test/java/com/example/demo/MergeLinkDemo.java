package com.example.demo;

import java.util.Arrays;

import com.example.util.LinkNode;
import com.example.util.SortUtil;

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
            System.out.print(merged.getData() + " ");
            merged = merged.getNext();
        }
        System.out.println("node complex: " + LinkNode.getComplex());

        int[] arr = new int[]{3, 7, 5, 6, 1, 9};

//        int com = SortUtil.popupSort(arr);
//        System.out.println("pop complex:" + com);

        SortUtil.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
}
