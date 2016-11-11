package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
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
        Date d = new Date();
        for(int i = 0; i < NUM; i++) {
            Member m = new Member("id_" + i, "name_" + i, 1, 0.0, i % 2 == 0 ? "male" : "female");
            m.setBirthday(DateUtils.addDays(d, i));
            members.add(m);
        }
        long a1 = System.currentTimeMillis();
        test3();
        long b1 = System.currentTimeMillis();
        System.out.println("********" + (b1 - a1));
        long a = System.currentTimeMillis();
        test2();
        long b = System.currentTimeMillis();
        System.out.println("********" + (b - a));

        members.stream().limit(5).forEach(member -> System.out.println(member.getBirthday()));
        System.out.println("********");

        System.out.println(System.currentTimeMillis());
    }

    private void test2() {
        List<Member> other = members.stream().sorted(Comparator.comparing(Member::getBirthday).reversed()).collect(Collectors.toList());
    }

    private void test3() {
        List<Member> other = members.parallelStream().sorted(Comparator.comparing(Member::getBirthday).reversed()).collect(Collectors.toList());
    }
}
