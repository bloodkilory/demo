package com.example.service;

import org.springframework.stereotype.Service;

/**
 * @author yangkun
 *         generate on 16/6/5
 */
@Service
public class RedisListenerServiceImpl implements RedisListenerService {
    @Override
    public String handleMessage(String message, String channel) {
        System.out.println("channel:" + channel + ", message:" + message);
        return null;
    }
}
