package com.example.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.Application;
import com.example.config.LocalRedisConfig;
import com.example.dao.DictionaryDao;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@ContextConfiguration(classes = {Application.class, LocalRedisConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryDaoTest {
	@Resource
	private DictionaryDao dictionaryDao;

	@Resource(name = "redisTemplate")
	private StringRedisTemplate redisTemplate;

	@Test
	public void pushTest() {

	}

	@Test
	public void listTest() {
		System.out.println("\n****" + dictionaryDao.list("nothing") + "****\n");
	}
}
