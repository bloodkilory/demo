package com.example.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.Application;
import com.example.service.RedisListenerService;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@ContextConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisListenerTest {
    @Resource
    RedisListenerService redisListenerService;

    @Resource
    private StringRedisTemplate redisTemplate;

    @Test
    public void testHandleMessage() {
        while(true) {
            // 启动应用程序来测试接收消息
        }
    }

}
