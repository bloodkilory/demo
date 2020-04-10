package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author yangkun
 *         generate on 16/11/11
 */
@Service
public class TraceService {

    private static final Logger LOGGER = Logger.getLogger(TraceService.class);

    private ScheduledExecutorService executorService =
            new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.CallerRunsPolicy());

    final private ConcurrentMap<String, Boolean> flag = new ConcurrentHashMap<>();

    /**
     * 模拟主逻辑
     * @param orderNo
     */
    public void exe(String orderNo) {
        try {
            final Map<String, Future> futures = new HashMap<>();
            Future future = executorService.scheduleAtFixedRate(() -> {

                if(Boolean.TRUE.equals(flag.get(orderNo))) {
                    Future f = futures.get(orderNo);
                    if(f != null) f.cancel(true);
                    flag.remove(orderNo);
                }

                try {
                    String result = Thread.currentThread().getName(); //模拟获取数据
                    save(orderNo, result); //模拟存储数据
                } catch(Throwable t) {
                    LOGGER.error("something wrong!", t);
                }

            }, 0, 3, TimeUnit.SECONDS);
            futures.put(orderNo, future);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 模拟存储数据
     * @param orderNo
     */
    public void save(String orderNo, String result) {
        LOGGER.info(String.format("Order:%s saved. Result:%s", orderNo, result));
    }

    public void terminite(String orderNo) throws Exception{
        flag.put(orderNo, true);
        LOGGER.info("task "+orderNo+" terminited!");
    }

    @PreDestroy
    public void destory() {
        executorService.shutdownNow();
        LOGGER.info("executor shutdown!");
    }

}
