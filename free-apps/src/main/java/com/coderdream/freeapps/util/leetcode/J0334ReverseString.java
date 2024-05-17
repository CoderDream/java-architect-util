package com.coderdream.freeapps.util.leetcode;

/**
 * @author CoderDream
 */
public class J0334ReverseString {

    public static void main(String[] args) {
        char[] input1 = {'h', 'e', 'l', 'l', 'o'};
        Solution solution = new Solution();
        solution.reverseString(input1);
        System.out.println(input1);

        char[] input2 = {'h', 'e', 'l', 'l', 'o'};
        Solution2 solution2 = new Solution2();
        solution2.reverseString(input2);
        System.out.println(input2);
    }
}

class Solution {

    public void reverseString(char[] s) {
        long startTime = System.nanoTime();
        int length = s.length;
        int size = length / 2;
        char tempChar;
        for (int i = 0; i < size; i++) {
            tempChar = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = tempChar;
        }
        long period = System.nanoTime() - startTime;
        System.out.println("1:\t" + period);
    }
}

class Solution2 {

    /**
     * 双指针
     */
    public void reverseString(char[] s) {
        long startTime = System.nanoTime();
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
        long period = System.nanoTime() - startTime;
        System.out.println("2:\t" + period);
    }
}
