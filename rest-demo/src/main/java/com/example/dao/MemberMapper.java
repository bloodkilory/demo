package com.example.dao;

import com.example.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bloodkilory
 *         generate on 15/5/10
 */
public interface MemberMapper {
	@Select("SELECT * FROM member WHERE mid=#{mid}")
	Member findById(String mid);

	@Select("SELECT * FROM member")
	List<Member> findAll();

	@Insert("INSERT INTO member(mid, name, age, sex, salary)" +
			" values(#{mid}, #{name}, #{age}, #{sex}, #{salary})")
	void doCreate(Member member);
}
