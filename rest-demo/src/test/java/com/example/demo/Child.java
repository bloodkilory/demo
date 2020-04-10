package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkun
 * generate on 2017/11/15
 */
public class Child extends Father {

    public void sysout(Map map) {
        System.out.println("child");
    }

    public void sysout(HashMap map) {
        super.sysout(map);
    }

    public static void main(String[] args) {
        Child c = new Child();
        Map map = new HashMap();
        c.sysout(map);
        c.sysout(new HashMap());


    }
}
