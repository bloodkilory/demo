package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author yangkun
 *         generate on 15/7/20
 *         分别使用三种方法来解决'求子字符串出现的次数'
 */
public class countWord {
	@Test
	public void testWithApacheCommon() {
		String str = "andjijkrlejladcdsaaakji";
		int a = StringUtils.countMatches(str, "a");
		TestCase.assertEquals(a, 5);
	}

	@Test
	public void testWithJDK() {
		String str = "hello my lovely world";
		String str2 = str.replaceAll("o", "");
		TestCase.assertEquals(str.length() - str2.length(), 3);
	}

	@Test
	public void testWithRegex() {
		String str = "xjiejljxadfwxjlkax";
		Pattern pattern = Pattern.compile("x");
		Matcher matcher = pattern.matcher(str);
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		TestCase.assertEquals(count, 4);
	}
}
