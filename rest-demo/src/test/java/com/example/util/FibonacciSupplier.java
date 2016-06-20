package com.example.util;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 使用JDK1.8 Stream接口生成斐波那契数列
 *
 * @author bloodkilory
 *         generate on 15/6/13
 */
public class FibonacciSupplier implements Supplier<Long> {

	long a = 0;
	long b = 1;

	@Override
	public Long get() {
		long x = a + b;
		a = b;
		b = x;
		return a;
	}

	public static void main(String[] args) {
		Stream<Long> fibonacci = Stream.generate(new FibonacciSupplier());
		fibonacci.limit(15).forEach(System.out::println);
//		List<Long> fiblist = fibonacci.skip(10).limit(20).collect(Collectors.toList());
//		System.out.println(fiblist);

		System.out.println(fibonacci(0, 1));
	}

	/**
	 * 递归实现
	 *
	 * @param _a
	 * @param _b
	 * @return
	 */
	public static long fibonacci(long _a, long _b) {
		if(_a + _b > 100) {
			return _a + _b;
		}
		long x = _a + _b;
		_a = _b;
		_b = x;
		return fibonacci(_a, _b);
	}
}
