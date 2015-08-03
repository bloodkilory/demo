package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author yangkun
 *         generate on 15/7/29
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
	String desc();

	String author();

	int age() default 18;
}
