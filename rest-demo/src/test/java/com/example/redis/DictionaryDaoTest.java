package com.example.redis;

import com.example.config.LocalRedisConfig;
import com.example.dao.DictionaryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@ContextConfiguration(classes = LocalRedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryDaoTest {
	@Resource
	private DictionaryDao dictionaryDao;

	@Resource
	private StringRedisTemplate redisTemplate;

	@Test
	public void pushTest() {
		String meaning = "this is no meaning";
		Long index = dictionaryDao.add("nothing", meaning);
		System.out.println(index);
	}

	@Test
	public void listTest() {
		System.out.println("\n****" + dictionaryDao.list("nothing") + "****\n");
	}
}
