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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 解法二：优化解法思路：
     *  1.整数转字符串，再转字符数组
     *  2.交换首位（start）和末位（end）数字
     *  3.循环操作：依次交换第二（start++）和倒数第二个(end--)，直到数组剩下1个或0个元素
     *  4.将原数组转成字符串，再转成整数输出
     *
     * 注意事项：
     *  边界问题
     *    数组索引越界：数组长度为偶数，反转完成标志为start>end；
     *                      为奇数，反转完成标志为start==end
     *    数值溢出边界：溢出则返回0
     *  细节问题：
     *    首位不为0
     *    符号处理
     *
     * @param x 指定整数
     * @return 反转后的整数，或0
     */
    public int reverse(int x) {
        if(x == Integer.MIN_VALUE || x == Integer.MIN_VALUE) {
            // 整数类型的最小值的绝对值 比 最大值的绝对值 大 1
            return 0;
        }

        // 判断正负
        int sign =  x > 0 ? 1 : -1;// 符号
        x = x < 0 ? -x: x; // 无论正负，都当成正数

        // 1.整数转字符串，再转字符数组
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        // 2.交换首位（start）和末位（end）数字
        // 3.循环操作：依次交换第二（start++）和倒数第二个(end--)，直到数组剩下1个或0个元素
        int start = 0, end = chars.length - 1;
        while (start < end) { // 反转完成的标志： start >= end
            // 交换两端等距离的元素
            char temp = chars[start];
            chars[start]  = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }

        // 4.将新数组转成字符串，再转成整数输出
        long value = Long.valueOf(String.valueOf(chars));
        boolean b = value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;

        int result = b ? 0 : (int)value; // 数值越界：溢出则返回0

        return result * sign;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
