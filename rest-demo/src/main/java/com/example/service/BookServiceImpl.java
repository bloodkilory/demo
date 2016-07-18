package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookDao;
import com.example.pojo.Book;

/**
 * @author yangkun
 *         generate on 16/7/13
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> listBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book getBook(int id) {
        return bookDao.findById(id);
    }
}
