package com.example.test;

import com.example.client.ApacheRestClient;
import org.junit.Test;

/**
 * Created by bloodkilory on 15/5/10.
 */
public class TestRestClient {

	@Test
	public void test1() {
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

}
