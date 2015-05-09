package com.adcc.mydemo.pojo;

import java.io.Serializable;

/**
 * Created by Yangkun on 15-4-27.
 */
public class Book implements Serializable{

	private Long id;

	private String name;

	private Integer page;

	private String comment;

	public Book() {
	}

	public Book(Long id, String name, Integer page, String comment) {
		this.id = id;
		this.name = name;
		this.page = page;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
