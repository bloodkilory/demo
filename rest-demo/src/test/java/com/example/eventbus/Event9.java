package com.example.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author yangkun
 *         generate on 16/8/19
 */
public class Event9 {

    @Subscribe
    public void test(Notify2 msg) {
        System.out.println("事件9 响应: " + msg.key);
    }
}
