package com.interview.algorithms.recursion;

public class D01Sum100 {

    public static void main(String[] args) {
        System.out.println(sumFor(100));
        System.out.println(sumRecursion(100));
    }

    public static int sumFor(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static int sumRecursion(int n) {
        if (n == 1) {
            return 1;// 递归出口（终止递归的条件）
        } else {
            return sumFor(n - 1) + n; // 递归表达式（规律）
        }
    }

}
