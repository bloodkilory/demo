package com.example.test;

import com.example.client.ApacheRestClient;
import com.example.pojo.Member;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bloodkilory on 15/5/10.
 */
public class TestRestClient {

	@Test
	public void testAdmin() {
		ApacheRestClient<String, String> client = new ApacheRestClient<>();
		String host = "localhost";
		String port = "8080";
		String path = "/books";
		String accept = ApacheRestClient.JSON;
		String username = "admin";
		String password = "admin";
		String response = client.restWithSecurity(ApacheRestClient.GET, host, port, path, accept, username, password);
		System.out.println(response);
	}

	@Test
	public void testAny() {
		ApacheRestClient<String, String> client = new ApacheRestClient<>();
		String host = "localhost";
		String port = "8080";
		String path = "/books/hello";
		String accept = ApacheRestClient.JSON;
		String username = null;
		String password = "";
		String response = client.restWithSecurity(ApacheRestClient.GET, host, port, path, accept, username, password);
		System.out.println(response);
	}

	@Test
	public void testMemberInsert() {
		ApacheRestClient<Member, String> client = new ApacheRestClient<>();
		String host = "localhost";
		String port = "8080";
		String path = "/members";
		String accept = ApacheRestClient.JSON;
		String username = "";
		String password = "";
		Member member1 = new Member("007", "James Bond", 39, 12_000_000_000D, "male");
		Member member2 = new Member("008", "LiXinHua", 36, 1_000_000D, "male");
		Member member3 = new Member("009", "Ann Su", 17, 6_000D, "female");
		Member member4 = new Member("010", "Huge Green", 54, 3_000.8D, "monster");
		Member member5 = new Member("011", "Big Jim", 56, 80_000.0D, "dead");
		String response = client.rest(ApacheRestClient.POST, host, port, path, accept, null, null, null, member5,
				username, password);
		System.out.println(response);
	}

	@Test
	public void testMemberGetAll() {
		ApacheRestClient<Void, List<Member>> client = new ApacheRestClient<>();
		String host = "localhost";
		String port = "8080";
		String path = "/members";
		String accept = ApacheRestClient.JSON;
		String username = "";
		String password = "";
		List<Member> re = new ArrayList<>();
		List<Member> response = client.rest(ApacheRestClient.GET, host, port, path, accept, List.class, null);
		// 此处会报java.util.LinkedHashMap cannot be cast to com.example.pojo.Member，范型只在编译期起作用
		for(Member member : response) {
			System.out.println(member.getName());
		}
	}

	@Test
	public void testMemberGetOne() {
		ApacheRestClient<Void, Member> client = new ApacheRestClient<>();
		String host = "localhost";
		String port = "8080";
		String path = "/members/member/009";
		String accept = ApacheRestClient.JSON;
		String username = "";
		String password = "";
		Member response = client.rest(ApacheRestClient.GET, host, port, path, accept, Member.class);
		System.out.println(response);
	}

}
