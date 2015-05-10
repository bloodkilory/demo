package com.example.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author bloodkilory
 *         generate on 15/5/10
 */
public class Member {
	private String mid;
	private String name;
	private int age;
	private double salary;
	private String sex;

	public Member() {
	}

	public Member(String mid, String name, int age, double salary, String sex) {
		this.mid = mid;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.sex = sex;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("mid", mid)
				.append("name", name)
				.append("age", age)
				.append("salary", salary)
				.append("sex", sex)
				.toString();
	}
}
