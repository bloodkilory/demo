package com.example.dao;

import com.example.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by bloodkilory on 15/5/5.
 */
@Repository("bookDaoImpl")
public class BookDaoImpl implements BookDao {

	private Map<Integer, Book> fakeDB;

	public BookDaoImpl() {
		Book b1 = new Book(1, "Java Development", 67.5);
		Book b2 = new Book(2, "Oracle Practical", 99);
		Book b3 = new Book(3, "RESTful API", 12.5);
		fakeDB = new HashMap<>(3);
		fakeDB.putIfAbsent(1, b1);
		fakeDB.putIfAbsent(2, b2);
		fakeDB.putIfAbsent(3, b3);
	}

	@Override
	public List<Book> findAll() {
		List<Book> list = new ArrayList<>(3);
		list.addAll(this.fakeDB.keySet().stream().map(this.fakeDB::get).collect(Collectors.toList()));
		return list;
	}

	@Override
	public Book findById(int id) {
		return this.fakeDB.get(id);
	}
}
