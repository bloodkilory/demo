package com.example.dao;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@Repository
public class DictionaryDao {
	@Resource
	private StringRedisTemplate redisTemplate;

	public Long add(String word, String meaning) {
		Long index = redisTemplate.opsForList().rightPush(word, meaning);
		return index;
	}

	public List<String> list(String word) {
		return redisTemplate.opsForList().range(word, 0, 3);
	}

}
