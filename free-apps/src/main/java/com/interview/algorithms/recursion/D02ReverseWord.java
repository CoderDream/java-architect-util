package com.interview.algorithms.recursion;

/**
 * @author CoderDream
 */
public class D02ReverseWord {

    public static void main(String[] args) {
        String word = "hello";
        char[] charArray = word.toCharArray();
        wordRecursion(charArray);
        System.out.println(charArray);
    }

    public static void wordRecursion(char[] charArray) {
        helper(0, charArray.length - 1, charArray);
    }

    /**
     * 一开始从第一个和最后一个字符交换，第二次从第二个和倒数第二个字符交换，依次交换；
     * 退出条件为start大于等于end
     * @param start
     * @param end
     * @param s
     */

    public static void helper(int start, int end, char[] s) {
        if (start >= end) {
            return;// 递归出口（终止递归的条件）
        }
        // 交换两个字符
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;

        helper(start + 1, end - 1, s);// 递归表达式（规律）
    }

}
