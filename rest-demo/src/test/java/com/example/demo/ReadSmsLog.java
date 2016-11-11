package com.example.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.base.Splitter;

/**
 * @author yangkun
 *         generate on 16/10/19
 */
public class ReadSmsLog {
    private static String fromPath = "/Users/bloodkilory/jiashuangkuaizi/files/logVoice.txt";

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(fromPath), StandardCharsets.UTF_8);
        int sum = 0;
        for(String line : lines) {
            String sub = line.substring(line.indexOf("phones:") + 7, line.indexOf(";content"));
            sum += Splitter.on(",").omitEmptyStrings().splitToList(sub).size();
        }
        System.out.println("总数: " + sum);
    }
}
