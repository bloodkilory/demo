package com.example.dao;

import com.example.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bloodkilory on 15/5/5.
 */
@Repository
public class UserDaoImpl implements UserDao {

	private Map<String, User> fakeDB;

	public UserDaoImpl() {
		String auth1 = "ROLE_ADMIN";
		String auth2 = "ROLE_USER";
		User u1 = new User("user", "user");
		User u2 = new User("admin", "admin");
		u2.setAuthorties(Arrays.asList(auth1, auth2));
		u1.setAuthorties(Collections.singletonList(auth2));
		fakeDB = new HashMap<>(2);
		fakeDB.putIfAbsent(u1.getName(), u1);
		fakeDB.putIfAbsent(u2.getName(), u2);
	}

	@Override
	public User findByName(String name) {
		return this.fakeDB.get(name);
	}
}
