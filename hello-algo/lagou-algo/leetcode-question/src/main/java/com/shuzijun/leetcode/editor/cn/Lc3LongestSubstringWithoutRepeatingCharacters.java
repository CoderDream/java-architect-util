//给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
//
// Related Topics 哈希表 字符串 滑动窗口 👍 10201 👎 0


package com.shuzijun.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CoderDream
 */
public class Lc3LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        Solution solution = new Lc3LongestSubstringWithoutRepeatingCharacters().new Solution();

        int a = solution.lengthOfLongestSubstring("abcabcbb");
        int b = solution.lengthOfLongestSubstring("bbbbb");
        int c = solution.lengthOfLongestSubstring("pwwkew");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * <pre>
         * 解法一：暴力解法，使用语言特性
         * 思路：
         *  先生成不含重复字符的子串，再统计最长子串的长度
         *
         * </pre>
         *
         * @param s
         * @return
         */
        public int lengthOfLongestSubstring(String s) {
            int length;
            if (s == null || (length = s.length()) == 0) {
                return 0;
            }
            int maxLength = 1;
            // 1.遍历字符串，生成所有的不含重复字符的子串
            for (int start = 0; start < length; start++) { // 遍历子串的起始字符
                for (int end = start + 1; end < length; end++) { // 遍历子串的终止字符
                    String subStr = s.substring(start, end); // 包含start，不包含end
                    // 当前字符在前面的子串中已出现，则跳过该字符
                    if (subStr.indexOf(s.charAt(end)) != -1) {
//                        System.out.println("subStr: " + subStr);
                        break;
                    }
                    // 2.统计最长子串的长度
                    int subLen = end + 1 - start; // 子串长度
                    if (subLen > maxLength) {
                        maxLength = subLen;
                    }
                }
            }

            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
