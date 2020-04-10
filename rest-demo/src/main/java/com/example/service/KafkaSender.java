package com.example.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.pojo.Message;
import com.google.common.collect.Lists;

/**
 * @author yangkun
 * generate on 2017/4/26
 */
@Service
public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String destination, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(destination, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.debug("Sent message='{}' to='{}' with offset={}", message, destination,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                String msg = String.format("Unable to send message='%s', to='%s'", message, destination);
                LOGGER.error(msg, ex);
            }
        });
    }

    @Scheduled(fixedRate = 30000)
    public void sendMessage() {
        List<Message> messages = Lists.newArrayList();
        for(int i = 0; i < 10000; i++) {
            messages.add(new Message(i, "cmd" + i, "des" + i, "cont" + i));
        }
        LOGGER.info("****** Sending Kafka... ******");
        for(Message m : messages) {
            send("test.queue.1", m.toString());
        }
        LOGGER.info("****** Sent Kafka. ******");
    }
}
