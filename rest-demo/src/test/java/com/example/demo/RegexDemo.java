package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangkun
 *         generate on 16/8/7
 */
public class RegexDemo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("c.*");
        Matcher matcher = pattern.matcher("com.tencent.android");
        System.out.println(matcher.matches());
    }
}
