package com.example.service;

import com.example.pojo.Member;

import java.util.List;

/**
 * @author bloodkilory
 *         generate on 15/5/10
 */
public interface MemberService {
	Member getById(String mid);

	List<Member> getAll();

	void insert(Member member);
}
