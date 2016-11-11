package com.example.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author yangkun
 *         generate on 16/8/19
 */
public class Event10 {


    @Subscribe
    public void sub(Notify1 msg) {
        System.out.println("事件10响应: " + msg.key);
    }
}
