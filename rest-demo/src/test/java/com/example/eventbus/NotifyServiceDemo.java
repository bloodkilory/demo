package com.example.eventbus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author yangkun
 *         generate on 16/8/19
 */
@Service
public class NotifyServiceDemo {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private EventBus eventBus = new AsyncEventBus(executor);

    @Test
    public void notification() {
        eventBus.register(this);
        eventBus.register(new Event10());
        eventBus.register(new Event7());
        eventBus.register(new Event9());
        eventBus.post(new Notify());
    }

    @Subscribe
    public void notice(Notify notify) {
        for(int i = 0; i <= 50; i++) {

            if(i % 10 == 0) {

                eventBus.post(Notify3.newEvent(i+""));
            } else if(i % 7 == 0) {

                eventBus.post(Notify2.newEvent(i+""));
            } else if(i % 9 == 0) {

                eventBus.post(Notify1.newEvent(i+""));
            }
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
