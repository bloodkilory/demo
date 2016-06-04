package com.example.redis;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

import com.example.Application;
import com.example.dao.PersonDao;
import com.example.pojo.Person;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
@ContextConfiguration(classes = {Application.class})
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

	@Test
	public void addAllTest() {
		Person p1 = new Person("1", "A", 19);
		Person p2 = new Person("2", "B", 22);
		Person p3 = new Person("3", "C", 29);
		Person p4 = new Person("4", "D", 11);
		Person p5 = new Person("5", "E", 3);
		Person p6 = new Person("6", "F", 44);
		Map<String, Person> map = new HashMap<>();
		map.putIfAbsent(p1.getId(), p1);
		map.putIfAbsent(p2.getId(), p2);
		map.putIfAbsent(p3.getId(), p3);
		map.putIfAbsent(p4.getId(), p4);
		map.putIfAbsent(p5.getId(), p5);
		map.putIfAbsent(p6.getId(), p6);
		this.personDao.addAll(map);
	}

	@Test
	public void listTest() {
		TestCase.assertEquals(this.personDao.list().size(), 6);
	}
}