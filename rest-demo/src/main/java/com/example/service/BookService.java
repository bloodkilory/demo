package com.example.service;

import java.util.List;

import com.example.pojo.Book;

/**
 * @author yangkun
 *         generate on 16/7/13
 */
public interface BookService {
    List<Book> listBooks();

    Book getBook(int id);
}
