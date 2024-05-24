//有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
//
//
// 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
//
//
// 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
//
//
// 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
//
// 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
//
//
//
// 示例 1：
//
//
//输入：s = "(()())(())"
//输出："()()()"
//解释：
//输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
//删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
//
// 示例 2：
//
//
//输入：s = "(()())(())(()(()))"
//输出："()()()()(())"
//解释：
//输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
//删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
//
//
// 示例 3：
//
//
//输入：s = "()()"
//输出：""
//解释：
//输入字符串为 "()()"，原语化分解得到 "()" + "()"，
//删除每个部分中的最外层括号后得到 "" + "" = ""。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁵
// s[i] 为 '(' 或 ')'
// s 是一个有效括号字符串
//
//
// Related Topics 栈 字符串 👍 303 👎 0


package com.shuzijun.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderDream
 */
public class RemoveOutermostParentheses1021 {

    public static void main(String[] args) {
        Solution solution = new RemoveOutermostParentheses1021().new Solution();
        // 测试数据
        String[] arr = {"(()())(())", "(()())(())(()(()))", "()()"};
        for (int i = 0; i < arr.length; i++) {
            String result = solution.removeOuterParentheses(arr[i]);
            System.out.println(arr[i] + " ---- " + result);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 栈：后进先出（子弹匣）
         *
         * @param <E>
         */
        class Stack<E> {

            Object[] elements = new Object[10000];
            int index = -1; // 栈顶索引（当前栈顶所处的索引）
            int size = 0; // 栈中元素个数

            public Stack() {

            }

            /**
             * 往栈顶插入元素
             *
             * @param c
             */
            public void push(E c) {
                elements[++index] = c;
                size++;
            }

            /**
             * 取栈顶元素，并不移出
             *
             * @return
             */
            public E peek() {
                if (index < 0) {
                    return null;
                }
                return (E) elements[index];
            }

            /**
             * 从栈顶移出元素
             *
             * @return
             */
            public E pop() {
                E e = peek();
                if (e != null) {
                    elements[index] = null; // 移出动作（移出元素）
                    index--; // 栈顶下移
                    size--; // 元素格式减一
                }

                return e;
            }

            /**
             * 判断是否为空
             *
             * @return
             */
            public boolean isEmpty() {
                return size == 0;
            }

        }

//        /**
//         * <pre>
//         * 官方解答
//         * </pre>
//         */
//        public String removeOuterParentheses(String s) {
//            StringBuffer res = new StringBuffer();
//            Deque<Character> stack = new ArrayDeque<Character>();
//            for(int i = 0; i < s.length(); i++){
//                char c = s.charAt(i);
//                if(c == ')'){
//                    stack.pop();
//                }
//                if(!stack.isEmpty()){
//                    res.append(c);
//                }
//                if(c == '('){
//                    stack.push(c);
//                }
//            }
//            return res.toString();
//        }

        /**
         * <pre>
         * 最优解：代码优化：
         * 1.定义计数器count，统计左右括号出现的次数
         * 2.遍历字符串，根据读取数据进行计数
         * 3.根据计数情况判断左右括号是否属于原语
         * </pre>
         */
        public String removeOuterParentheses(String s) {
            StringBuilder result = new StringBuilder();
            // 1.定义计数器count，统计左右括号出现的次数
            int count = 0;
            int len = s.length();
            char[] arrs = s.toCharArray(); // 字符数组
            // 2.遍历字符串，根据读取数据进行计数
            for (int i = 0; i < len; i++) {
                char c = arrs[i];
                if (c == '(') {
                    // 3.根据计数情况判断左右括号是否属于原语
                    if (count > 0) { // 此前有数据，
                        result.append(c); // 则当前字符必然是原语的一部分
                    }
                    count++; // 读取到左括号自增
                } else {
                    count--; // 读取到右括号自增
                    // 3.根据计数情况判断左右括号是否属于原语
                    if (count > 0) { // 右括号匹配之后不为0，count自减后仍有数据，
                        result.append(c); // 则当前字符必然是原语的一部分
                    }
                }
            }
            // 4.将原字符拼接到缓冲区并返回
            return result.toString();
        }

        /**
         * <pre>
         * 最优解：代码优化：
         * 1.直接用数组取代栈
         * 创建数组、栈顶索引，使用数组操作入栈和出栈
         * 2.将原字符串转为数组进行遍历
         * char[] s = S.toCharArray();
         * 3.去掉截取子串的操作，将原语字符直接拼接
         * 读取到左括号：此前有数据，当前必属原语
         * 读取到右括号：匹配后不为0，当前必属原语
         * </pre>
         */
        public String removeOuterParenthesesV4(String s) {
            int len = s.length();
            StringBuilder result = new StringBuilder();
            // 1.直接用数组取代栈
            int index = -1; // 栈顶索引
            char[] stack = new char[len];
            // 创建数组、栈顶索引，使用数组操作入栈和出栈
            // char[] s = S.toCharArray();
            // 读取到右括号：匹配后不为0，当前必属原语

            // 2.将原字符串转为数组进行遍历
            char[] arrs = s.toCharArray();

            for (int i = 0; i < len; i++) {
                char c = arrs[i];
                // 读取到左括号，左括号入栈
                // 读取到左括号：此前有数据，当前必属原语
                if (c == '(') {
                    // 3.去掉截取子串的操作，将原语字符直接拼接
                    if (index > -1) { // 此前有数据，
                        result.append(c); // 则当前字符必然是原语的一部分
                    }
                    stack[++index] = c; // 遇到左括号，左括号入栈
                } else /*if (c == ')')*/ {
                    // 读取到右括号，左括号出栈
                    stack[index--] = '\u0000'; // 遇到右括号，左括号出栈
                    // 3.去掉截取子串的操作，将原语字符直接拼接
                    if (index > -1) { // 右括号匹配之后不为0，
                        result.append(c); // 则当前字符必然是原语的一部分
                    }
                }
            }

            return result.toString();
        }

        /**
         * <pre>
         * 最优解：栈解法
         * 1.使用数组模拟一个栈，临时存储左括号字符
         * push(Character) ; pop(); isEmpty()
         * 2.遍历字符串，根据情况进行入栈/出栈操作
         * 读取到左括号，左括号入栈
         * 读取到右括号，左括号出栈
         * 3.判断栈是否为空，若为空，找到了一个完整的原语
         * 4.截取不含最外层括号的原语子串并进行拼接
         * </pre>
         *
         * @param s
         * @return
         */
        public String removeOuterParenthesesV3(String s) {
            int len = s.length();
            StringBuilder result = new StringBuilder();
            // 1.使用数组模拟一个栈，临时存储左括号字符
            Stack<Character> stack = new Stack<Character>();
            int lastOpr = 0;
            // 2.遍历字符串，根据情况进行入栈/出栈操作
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                // 读取到左括号，左括号入栈
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    // 读取到右括号，左括号出栈
                    stack.pop();
                }
                // 3.判断栈是否为空，若为空，找到了一个完整的原语
                if (stack.isEmpty()) {
                    // 4.截取不含最外层括号的原语子串并进行拼接
                    result.append(s.substring(++lastOpr, i));// 去掉原语的最外层括号，并追加到结果
                    lastOpr = i + 1; // 往后找，再次初始化原语开始位置
                }
            }

            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
