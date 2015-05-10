package com.example.rest;

import com.example.pojo.Member;
import com.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bloodkilory
 *         generate on 15/5/10
 */
@RestController
@RequestMapping(value = "/members")
public class MemberResource {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member/{id}")
	public Member member(@PathVariable("id") String mid) {
		return memberService.getById(mid);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Member> members() {
		return memberService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody Member member) {
		memberService.insert(member);
	}
}
