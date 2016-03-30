package com.example.dao;

import java.util.List;

import com.example.pojo.Book;

/**
 * Created by bloodkilory on 15/5/5.
 */
public interface BookDao {
	List<Book> findAll();

	Book findById(int id);
}
