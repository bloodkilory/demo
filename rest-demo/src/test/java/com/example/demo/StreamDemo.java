package com.example.demo;

import com.example.pojo.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author bloodkilory
 *         generate on 15/6/13
 */
public class StreamDemo {

	private List<Book> testList;
	private Map<Integer, Book> testMap;

	@Before
	public void before() {
		this.testList = new ArrayList<>();
		for(int i = 0; i < 1_000_000; i++) {
			Book book = new Book(i, "java-" + i, Math.abs(new Random().nextInt() % 50 + 1));
			this.testList.add(book);
		}
	}

	/**
	 * 测试List集合根据某一字段进行分组
	 */
	@Test
	public void testGroup() {
		Objects.requireNonNull(this.testList);

		//使用传统方法进行分组
		final Map<Double, List<Book>> map2 = new HashMap<>();
		long st2 = System.currentTimeMillis();
		for(Book book : testList) {
			double price = book.getPrice();
			if(map2.get(price) == null) {
				List<Book> list = new ArrayList<>();
				list.add(book);
				map2.putIfAbsent(price, list);
			} else {
				List<Book> list = map2.get(price);
				list.add(book);
				map2.put(price, list);
			}
		}
		long et2 = System.currentTimeMillis();
		System.out.println("Traditional: " + TimeUnit.MILLISECONDS.toMillis(et2 - st2) + " ms");

		//使用流式计算对List进行分组
		long st = System.currentTimeMillis();
		final Map<Double, List<Book>> map = testList
				.stream()
				.collect(Collectors.groupingBy(Book::getPrice));
		long et = System.currentTimeMillis();
		System.out.println("Stream: " + TimeUnit.MILLISECONDS.toMillis(et - st) + " ms");

	}
}
