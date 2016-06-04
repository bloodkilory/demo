package com.example.redis;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.Application;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@ContextConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalRedisConfigTest {
	@Resource
	private JedisConnectionFactory jedisConnectionFactory;

	@Resource
	private StringRedisTemplate redisTemplate;

	@Test
	public void testJedisConnectionFactory() {
		assertNotNull(jedisConnectionFactory);
	}

	@Test
	public void testStringRedisTemplate() {
		assertNotNull(redisTemplate);
	}
}
