package com.example.dao;

import com.example.pojo.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	@Resource
	private RedisTemplate<String, Person> redisTemplate;

	@Override
	public void insert(Person person) {
		redisTemplate.opsForValue().set(person.getId(), person);
	}

	@Override
	public Person get(String id) {
		return redisTemplate.opsForValue().get(id);
	}

	@Override
	public void delete(String id) {
		redisTemplate.opsForValue().getOperations().delete(id);
	}
}
