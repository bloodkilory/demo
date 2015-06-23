package com.example.dao;

import com.example.pojo.Person;

import java.util.Map;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
public interface PersonDao {
	void insert(Person person);

	Person get(String id);

	void delete(String id);

	void addAll(Map<String, Person> persons);

	Map<String, Person> list();
}
