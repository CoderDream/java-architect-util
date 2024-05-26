//给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持
//一致 。然后返回 nums 中唯一元素的个数。
//
// 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
//
//
// 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不
//重要。
// 返回 k 。
//
//
// 判题标准:
//
// 系统会用下面的代码来测试你的题解:
//
//
//int[] nums = [...]; // 输入数组
//int[] expectedNums = [...]; // 长度正确的期望答案
//
//int k = removeDuplicates(nums); // 调用
//
//assert k == expectedNums.length;
//for (int i = 0; i < k; i++) {
//    assert nums[i] == expectedNums[i];
//}
//
// 如果所有断言都通过，那么您的题解将被 通过。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：2, nums = [1,2,_]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
//
//
// 示例 2：
//
//
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// -10⁴ <= nums[i] <= 10⁴
// nums 已按 非严格递增 排列
//
//
// Related Topics 数组 双指针 👍 3566 👎 0


package com.shuzijun.leetcode.editor.cn;

/**
 * @author CoderDream
 */
public class Lc26RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new Lc26RemoveDuplicatesFromSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * <pre>
         * 解法一：暴力解法
         *  1.遍历数组
         *  2.依次比较相邻的元素（i和i+1)
         *      不同：向后遍历，i++;
         *      相同：后面所有元素前移一位；
         *  3.每遇到重复元素，数组长度缩减 1
         *
         *  边界问题
         *      数组索引越界
         *      循环退出条件：遍历到length - 1时
         *  细节问题
         *      每次处理重复数据后，需要 i 与 i+1 再比较一次，避免掉落元素
         *      相邻元素不相等是，索引才能继续指向下一个元素
         * </pre>
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {

            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
