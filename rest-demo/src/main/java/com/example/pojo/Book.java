package com.example.pojo;

import java.io.Serializable;

/**
 * Created by bloodkilory on 15/4/17.
 */
public class Book implements Serializable{

	private static final long serialVersionUID = 7776734532327416886L;

	private int id;
	private String name;
	private double price;

	public Book() {
	}

	public Book(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
