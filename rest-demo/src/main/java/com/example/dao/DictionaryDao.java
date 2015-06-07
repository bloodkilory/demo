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
	private StringRedisTemplate stringRedisTemplate;

	public Long add(String word, String meaning) {
		return stringRedisTemplate.opsForList().rightPush(word, meaning);
	}

	public List<String> list(String word) {
		return stringRedisTemplate.opsForList().range(word, 0, 3);
	}

}
