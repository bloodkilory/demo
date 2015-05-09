package com.adcc.mydemo.resource;

import com.adcc.mydemo.pojo.Book;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yangkun on 15-4-27.
 */
@RestController
@RequestMapping("/books")
public class Resource {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public String hello() {
		return "Hello, you are admin";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	@Secured("USER")
	public Book book(@PathVariable("id") Long id) {
		return new Book(1L, "Gone with wind", 990, "你在犹豫什么");
	}

	@RequestMapping(value = "x")
	@Secured("X")
	public String x() {
		return "your role is X";
	}

	@RequestMapping(value = "any")
	public String norole() {
		return "anyone can access";
	}
}
