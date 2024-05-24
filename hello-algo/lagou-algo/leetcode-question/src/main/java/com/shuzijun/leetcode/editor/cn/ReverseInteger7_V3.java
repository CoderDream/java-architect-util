//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -2³¹ <= x <= 2³¹ - 1
//
//
// Related Topics 数学 👍 3988 👎 0


package com.shuzijun.leetcode.editor.cn;

/**
 * @author CoderDream
 */
public class ReverseInteger7_V3 {

    public static void main(String[] args) {
        Solution solution = new ReverseInteger7_V3().new Solution();
        // 测试数据
        int[] arr = {123, -123, 120, Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < arr.length; i++) {
            int result = solution.reverse(arr[i]);
            System.out.println(arr[i] + " ---- " + result);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * <pre>
         * 最优解法 数学解法思路：
         *  1.尝试拿个位数字
         *      对10取模运算得到个位数字
         *  2.让每一位数字变成个位数字
         *      先除以10，再对10取模得到十位数字
         *      循环上述操作
         *  3.将每一位数字计算累加
         *      将上次累加结果*10 + 新数字
         *
         * 注意事项：
         *  边界问题：
         *      从低位到高位处理，最高位结束
         *          最高位 / 10 == 0
         *          最高位 % 10 == 最高位
         *      数值溢出边界：溢出则返回0
         *          用long类型存放，溢出int则返回0
         *          新整数补充最后一位前判断溢出
         *  细节问题：
         *      首位不为0
         *      符号处理
         * </pre>
         *
         * @param x 指定整数
         * @param x
         * @return 反转后的整数，或0
         * @return
         */
        public int reverse(int x) {
            if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
                // 整数类型的最小值的绝对值 比 最大值的绝对值 大1
                return 0;
            }
            int sign = x > 0 ? 1 : -1; // 符号
            x = x < 0 ? -x : x; // 无论正负，都当成正数

            int result = 0; // 返回结果
            // 1.尝试拿个位数字：对10取模运算
            // 2.让每一位数字变成个位数字：先除以10，再对10取模得到十位数字
            int last = 0; // 末位
            while ( (last = x % 10) != x) { // x % 10 == x 则结束，否则继续
                // 3.将每一位数字计算累加：将上次累加结果*10 + 新数字
                result = result * 10 + last;
                x /= 10; // 除10操作
            }
            if(last != 0) { // 此时last是最高位，单独处理
                long re = result;
                re = re * 10 + last;
                // 判断是否越界
                if(re > Integer.MAX_VALUE || re < Integer.MIN_VALUE){
                    result = 0;
                } else {
                    result = (int)re;
                }
            }

            return result * sign; // 返回前进行符号处理
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}

