package com.example.executor;

import java.util.concurrent.*;

import com.example.pojo.WorkUnit;

/**
 * @author yangkun
 *         generate on 16/4/18
 */
public class ScheduledExcutor {
    private ScheduledExecutorService stpe;
    private ScheduledFuture<?> hndl;

    private BlockingQueue<WorkUnit<String>> lbq = new LinkedBlockingQueue<>();

    private void run() {
        stpe = Executors.newScheduledThreadPool(2);

        final Runnable msgReader = () -> {
            String nextMsg = lbq.poll().getWork();
            if(nextMsg != null) {
                System.out.println("Msg recivied: " + nextMsg);
            }
        };
        hndl = stpe.scheduleAtFixedRate(msgReader, 10, 10, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        final ScheduledFuture<?> myHndl = hndl;

        stpe.schedule(() -> myHndl.cancel(true), 10, TimeUnit.MILLISECONDS);
    }
}
