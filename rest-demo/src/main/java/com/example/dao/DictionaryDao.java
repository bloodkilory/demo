package com.example.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author bloodkilory
 *         generate on 15/6/4
 */
@Repository
public class DictionaryDao {

	@Resource(name = "redisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	public Long add(String word, String meaning) {
		return stringRedisTemplate.opsForList().rightPush(word, meaning);
	}

	public List<String> list(String word) {
		return stringRedisTemplate.opsForList().range(word, 0, 3);
	}

    public Boolean add1(String name) {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(name);
        return null;
    }

}
