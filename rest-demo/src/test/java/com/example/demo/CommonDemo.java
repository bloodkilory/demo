package com.example.demo;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * @author yangkun
 * generate on 16/6/21
 */
public class CommonDemo {


    public static void main(String[] args) throws Exception {
        System.out.println(99 >> 1);
        HashSet a = new HashSet();
        a.stream().sorted().forEach((i) -> {

        });

    }

    private static void files(File[] files) throws IOException {
        if(files == null) {
            return;
        }
        for(File file : files) {
            if(file.isDirectory()) {
                if(file.getAbsolutePath().contains("MUSIC/netease")) {
                    continue;
                }
                files(file.listFiles());
            } else {
                Files.copy(Paths.get(file.getAbsolutePath()), Paths.get("/Volumes/SD_CARD/MUSIC/netease"), StandardCopyOption
                        .COPY_ATTRIBUTES);
            }
        }
    }

    private static List<String> ca(List<String> first, List<String> next) {
        List<String> newo = Lists.newArrayList();
        for(String aFirst : first) {
            for(String aNext : next) {
                newo.add(aFirst + aNext);
            }
        }
        return newo;
    }

    private static void add(Integer a) {
        a = a + 1;
    }

    public static boolean isAllEmpty(String str) {
        return Strings.isNullOrEmpty(str) || str.equals("\r") || str.equals("\n") || str.equals("\t");
    }

    @Test
    public void test() {
        System.out.println(1 ^ 1);
        System.out.println(1 ^ 0);
        System.out.println(0 ^ 1);
        System.out.println(0 & 0);
        System.out.println(Integer.toBinaryString(16));
        System.out.println(23343 & 15);
        System.out.println(231111 & 15);
        System.out.println(23 & 15);
    }
}
