package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.MemberMapper;
import com.example.pojo.Member;

/**
 * @author bloodkilory
 *         generate on 15/5/10
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member getById(String mid) {
		return memberMapper.findById(mid);
	}

	@Override
	public List<Member> getAll() {
		return memberMapper.findAll();
	}

	@Override
	@Transactional
	public void insert(Member member) {
		memberMapper.doCreate(member);
	}
}
