package com.example.demo;

import com.example.pojo.Book;

/**
 * @author yangkun
 *         generate on 16/5/17
 */
public class BytecodeDemo {
    int showTime;
    String images;

    public int getValue() {
        return 1;
    }

    public String getName() {
        return "name";
    }

    public int getShowTime() {
        return this.showTime * 1000;
    }

    public String getImages() {
        return this.images;
    }

    public static void main(String[] args) {
        try {
            testExceptionMessage();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void testExceptionMessage() {
        Book b = null;
        try {
            b.getId();


        } catch(Exception ex) {
            throw new RuntimeException("error!!");
        }
    }
}
