package com.example.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author yangkun
 * generate on 2017/4/26
 */
@Service
public class KafkaReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @KafkaListener(topics = {"test.queue.1"}, group = "comsumer1")
    public void onMessage(String msg) {
        LOGGER.info("****** 1.Receiving Kafka Message: " + msg);
    }

}
