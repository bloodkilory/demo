package com.example.redis;

import com.example.config.LocalRedisConfig;
import com.example.dao.PersonDao;
import com.example.pojo.Person;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
@ContextConfiguration(classes = LocalRedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {

	@Resource
	private PersonDao personDao;

	@Test
	public void testInsert() {
		this.personDao.insert(new Person("p1", "Alice", 18));
	}

	@Test
	public void testGet() {
		TestCase.assertEquals(this.personDao.get("p1").getName(), "Alice");
		System.out.println("\n***" + this.personDao.get("p1") + "***\n");
	}

	@Test
	public void testDelete() {
		this.personDao.delete("p1");
	}
}