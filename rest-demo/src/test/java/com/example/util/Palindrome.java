package com.example.util;

/**
 * 判断字符串是否回文，两种方式
 *
 * @author yangkun
 * generate on 16/12/5
 */
public class Palindrome {

    /**
     * 这个太糟糕了
     *
     * @param str
     * @return
     */
    public static boolean isPalin(String str) {
        if(str == null) return false;
        if(str.trim().equals("")) return true;
        StringBuilder bud = new StringBuilder();
        for(String s : str.toLowerCase().split("")) {
            if(s.matches("[a-z]|[0-9]")) {
                bud.append(s);
            }
        }
        String ss = bud.toString();
        boolean isOdd = ss.length() % 2 == 1;
        int splitIndex = isOdd ? ss.length() / 2 : ss.length() / 2 - 1;
        String sub = ss.substring(0, splitIndex);
        String sub2 = ss.substring(isOdd ? splitIndex : splitIndex + 2);
        StringBuilder b1 = new StringBuilder(sub);
        String b2 = isOdd ? sub2.substring(1) : sub2;
        return b1.reverse().toString().equals(b2);
    }

    public static boolean isPalin2(String str) {
        if(str == null) return false;
        if(str.trim().equals("")) return true;
        StringBuilder bud = new StringBuilder();
        for(String s : str.toLowerCase().split("")) {
            if(s.matches("[a-z]|[0-9]")) {
                bud.append(s);
            }
        }
        String st = bud.toString();
        int i = 0;
        int j = st.length() - 1;
        while(i < j) {
            if(st.charAt(i) == st.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalin3(String s) {
        String[] arr = s.split("");
        int i = 0;
        int j = arr.length - 1;
        while(i < j) {
            while(i < j && !arr[i].matches("[a-z]|[A-Z]")) {
                i++;
            }
            while(i < j && !arr[j].matches("[a-z]|[A-Z]")) {
                j--;
            }
            if(i < j) {
                if(!arr[i].equalsIgnoreCase(arr[j])) {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String simbol1 = "Niagara, O, roar again.";
        String simbol2 = "Red root put up to order.";
        String simbol3 = "No lemons, no melon.";
        String err4 = "lemons, no melon.";

        System.out.println(isPalin3(simbol1));
        System.out.println(isPalin3(simbol2));
        System.out.println(isPalin3(simbol3));
        System.out.println(isPalin3(err4));

    }
}
