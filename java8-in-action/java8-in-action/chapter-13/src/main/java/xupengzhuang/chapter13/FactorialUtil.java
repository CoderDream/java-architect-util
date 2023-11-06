package xupengzhuang.chapter13;

import java.util.stream.IntStream;

public class FactorialUtil {
    /**
     * 迭代求阶乘
     * @param n
     * @return
     */
    public static int factorialIterative(int n){
        int sum = 1;
        for (int i=1;i<=n;i++){
            sum *= i;
        }
        return sum;
    }

    /**
     * 递归求阶乘
     * @param n
     * @return
     */
    public static int factorialRecursive(int n){
        return n == 1 ? 1 : n * factorialIterative(n-1);
    }

    /**
     * 函数流式求递归
     * @param n
     * @return
     */
    public static int factorialStream(int n){
        return IntStream.rangeClosed(1,n).reduce(1,(a,b) -> a * b);
    }

    /**
     * 尾递归求阶乘
     * @param n
     * @return
     */
    public static int factorialTailRecursive(int n){
        return factorialHelper(1,n);
    }

    private static int factorialHelper(int acc,int n){
        return n == 1 ? acc : factorialHelper(acc*n,n-1);
    }
}
