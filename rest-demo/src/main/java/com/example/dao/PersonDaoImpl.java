package com.example.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.pojo.Person;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	@Resource(name = "objectRedisTemplate")
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

	@Override
	public void addAll(Map<String, Person> persons) {
		redisTemplate.opsForHash().putAll("persons", persons);
	}

	@Override
	public Map<String, Person> list() {
		HashOperations<String, String, Person> operations = redisTemplate.opsForHash();
		return operations.entries("persons");
	}
}
