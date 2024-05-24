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
public class ReverseInteger7 {

    public static void main(String[] args) {
        Solution solution = new ReverseInteger7().new Solution();
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
         * 官方解答：
         * 方法1：按位转换
         * - 明确 % 运算类型
         *      求模
         *      求余
         *  - 【弹出】和【推入】数字
         *      弹出：num = x % 10; x /= 10;
         *      推入：result = result * 10 + num;
         *  - 模式识别：整数运算注意溢出
         *      转换为 INT_MAX / INT_MIN 的逆运算
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

            while (x != 0) {
                if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                    return 0;
                }

                int num = x % 10;
                x /= 10;

//                if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && num > Integer.MAX_VALUE % 10)) {
//                    return 0;
//                }
//
//                if(result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && num < Integer.MIN_VALUE % 10)) {
//                    return 0;
//                }

                result = result * 10 + num;
            }

            return result * sign; // 返回前进行符号处理
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}

