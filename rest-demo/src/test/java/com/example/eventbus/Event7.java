package com.example.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author yangkun
 *         generate on 16/8/19
 */
public class Event7 {


    @Subscribe
    public void test(Notify3 msg) {
        System.out.println("事件7 响应: " + msg.key);
    }
}
