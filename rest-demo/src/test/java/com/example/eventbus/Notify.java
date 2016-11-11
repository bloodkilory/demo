package com.example.eventbus;

/**
 * @author yangkun
 *         generate on 16/8/23
 */
public class Notify {

    public String key;

    public static Notify newEvent(String key) {
        Notify e_10 = new Notify();
        e_10.key = key;
        return e_10;
    }
}
