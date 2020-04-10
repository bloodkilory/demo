package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.example.pojo.Member;

/**
 * @author yangkun
 *         generate on 16/4/23
 */
public class Java8Test {

    private final Integer NUM = 30000;

    private List<Member> members = new ArrayList<>(NUM);

    @Test
    public void test1() {
    }

    private void test2() {
        List<Member> other = members.stream().sorted(Comparator.comparing(Member::getBirthday).reversed()).collect(Collectors.toList());
    }

    private void test3() {
        List<Member> other = members.parallelStream().sorted(Comparator.comparing(Member::getBirthday).reversed()).collect(Collectors.toList());
    }
}
