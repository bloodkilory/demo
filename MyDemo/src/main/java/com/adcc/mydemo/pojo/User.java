package com.adcc.mydemo.pojo;

import java.util.List;

/**
 * Created by Yangkun on 15-5-5.
 */
public class User {
	private String name;
	private String password;
	private List<String> authorities;

	public User() {
	}

	public User(String name, String password, List<String> authorities) {
		this.name = name;
		this.password = password;
		this.authorities = authorities;
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

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
