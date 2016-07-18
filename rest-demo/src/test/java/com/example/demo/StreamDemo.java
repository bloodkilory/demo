package com.example.demo;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.example.pojo.Book;

/**
 * @author bloodkilory
 *         generate on 15/6/13
 *         测试Stream接口类
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

		//使用流式计算对List进行分组
		long st = System.currentTimeMillis();
		final Map<Double, List<Book>> map = testList
				.stream()
				.collect(Collectors.groupingBy(Book::getPrice));
		long et = System.currentTimeMillis();
		System.out.println("Stream: " + TimeUnit.MILLISECONDS.toMillis(et - st) + " ms");

	}

	/**
	 * 测试过滤器
	 */
	@Test
	public void testFilter() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> twoEvenSquares =
				numbers.stream()
						.filter(n -> {
							System.out.println("filtering " + n);
							return n % 2 == 0;
						})
						.map(n -> {
							System.out.println("mapping " + n);
							return n * n;
						})
						.limit(2)
						.collect(Collectors.toList());
		System.out.println(twoEvenSquares);
	}

	/**
	 * 测试集合字段匹配
	 */
	@Test
	public void testMatch() {
		boolean b = this.testList.stream().allMatch(t -> t.getPrice() > 100);
	}

	/**
	 * 测试排序
	 */
	@Test
	public void testToList() {
		List<String> bookNames = this.testList.stream()
				.filter(b -> b.getPrice() < 30)
				.sorted(Comparator.comparing(Book::getPrice))
				.map(Book::getName)
				.collect(Collectors.toList());
	}

	/**
	 * 测试规约
	 */
	@Test
	public void testReduce() {
		List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
		int num = arr.stream().reduce(0, (a, b) -> a + b);
		System.out.println(num);
		int num2 = arr.stream().reduce(0, Integer::sum);
		System.out.println(num2);
		Optional<Integer> num3 = arr.stream().reduce((a, b) -> a + b);
		System.out.println(num3.get());
	}

	/**
	 * 测试最大最小值
	 */
	@Test
	public void testMinMax() {
		List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> max = arr.stream().reduce(Integer::max);
		System.out.println(max.get());
		Optional<Integer> min = arr.stream().reduce(Integer::min);
		System.out.println(min.get());
	}

	/**
	 * 测试流元素个数
	 */
	@Test
	public void testCount() {
		List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
		Stream<Integer> s1 = arr.stream();
		Stream<Integer> s2 = arr.stream();
		long count = s1.count();
		int c2 = s2.map(a -> 1).reduce(0, Integer::sum);
		System.out.println(count + "," + c2);
	}
}
