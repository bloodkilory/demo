package com.example.dao;

import com.example.pojo.User;

/**
 * Created by bloodkilory on 15/5/5.
 */
public interface UserDao {
	User findByName(String name);
}
