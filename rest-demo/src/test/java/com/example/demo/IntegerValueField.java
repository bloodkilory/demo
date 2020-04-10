package com.example.demo;

/**
 * @author yangkun
 * generate on 2017/11/18
 * Integer值域范围-128~127
 */
public class IntegerValueField {
    public static void main(String[] args) throws Exception {
        Integer a = 200;
        Integer b = 200;
        System.out.println(a == b);
        Integer a1 = -128;
        Integer b1 = -128;
        System.out.println(a1 == b1);
        Integer a2 = new Integer(1);
        Integer b2 = new Integer(1);
        Integer aa = 127;
        Integer bb = 127;
        System.out.println(a2 == b2);
        System.out.println(aa == bb);
    }
}
