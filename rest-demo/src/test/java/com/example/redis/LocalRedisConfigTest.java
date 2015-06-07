package com.example.redis;

import com.example.config.LocalRedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@ContextConfiguration(classes = LocalRedisConfig.class)
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
