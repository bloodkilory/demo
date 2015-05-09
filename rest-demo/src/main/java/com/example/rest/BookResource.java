package com.example.rest;

import com.example.dao.BookDao;
import com.example.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bloodkilory on 15/5/5.
 */
@RestController
@RequestMapping("/books")
public class BookResource {

	@Autowired
	private BookDao bookDao;

	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@Secured("ROLE_ADMIN")
	public List<Book> books() {
		return this.bookDao.findAll();
	}

	@RequestMapping(value = "/book/{id}")
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Book book(@PathVariable("id") int id) {
		return this.bookDao.findById(id);
	}

	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello everybody";
	}

}
