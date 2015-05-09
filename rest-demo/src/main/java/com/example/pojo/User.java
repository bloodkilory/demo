package com.example.pojo;

import java.util.List;

/**
 * Created by bloodkilory on 15/5/5.
 */
public class User {
	private String name;
	private String password;
	private List<String> authorties;

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorties() {
		return authorties;
	}

	public void setAuthorties(List<String> authorties) {
		this.authorties = authorties;
	}
}
