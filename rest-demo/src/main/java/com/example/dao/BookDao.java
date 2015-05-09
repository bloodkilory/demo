package com.example.dao;

import com.example.pojo.Book;

import java.util.List;

/**
 * Created by bloodkilory on 15/5/5.
 */
public interface BookDao {
	public List<Book> findAll();

	public Book findById(int id);
}
