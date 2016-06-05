package com.example.service;

/**
 * @author yangkun
 *         generate on 16/6/5
 */
public interface RedisListenerService {
    String handleMessage(String message, String channel);
}
