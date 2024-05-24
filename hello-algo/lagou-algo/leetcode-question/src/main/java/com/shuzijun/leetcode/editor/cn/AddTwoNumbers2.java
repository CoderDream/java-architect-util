//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
//
// Related Topics 递归 链表 数学 👍 10591 👎 0


package com.shuzijun.leetcode.editor.cn;

/**
 * @author CoderDream
 */
public class AddTwoNumbers2 {

    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers2().new Solution();
        int[] arr1 = {2, 4, 3};
        int[] arr2 = {5, 6, 4};
        ListNode l1 = new AddTwoNumbers2().new ListNode();
        ListNode l2 = new AddTwoNumbers2().new ListNode();

        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        for (int i = 0; i < arr1.length; i++) {
            ListNode node1 = new AddTwoNumbers2().new ListNode(arr1[i]);
            ListNode node2 = new AddTwoNumbers2().new ListNode(arr2[i]);
            l1Cur.next = node1;
            l1Cur = node1;
            l2Cur.next = node2;
            l2Cur = node2;
        }

        ListNode result = solution.addTwoNumbers(l1.next, l2.next);
        while (result != null) {
            System.out.print(result.val + "  ");
            result = result.next;
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     *
     * </pre>
     */
    class Solution {

        /**
         * <pre>
         * 最优解：数学思维解法
         *  1.遍历两个链表
         *  2.对应位置的节点数值相加
         *  3.将计算结果插⼊入新链表尾部
         *      ⼤大于10，则进位，将进位加到下个节点
         *
         * 边界问题
         *  两个链表边界：next==null
         * 细节问题
         *  两个链表⻓长度不不⼀一致，短链表⾼高位视为0
         *  链表最⾼高位发⽣生进位，结果链表需要增加⼀一个节点存放进位数字
         * </pre>
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p = l1, q = l2; // 原链表的两个遍历指针
            ListNode resultHead = new ListNode(-1); // 结果链表的头结点head
            ListNode curr = resultHead; // 结果链表的遍历指针，代表当前操作的节点

            int carry = 0; // 进位
            // 1.遍历两个链表
            while (p != null || q != null) { // 以长链表为准
                // 获取当前节点的值：链表较短，已无节点，取0
                int x = p != null ? p.val : 0;
                int y = q != null ? q.val : 0;
                // 2.对应位置的节点数值相加
                int sum = x + y + carry;
                carry = sum / 10; // 如何得到进位：和对10求整，得到此次计算的进位
                int num = sum % 10; // 存放到新链表节点中的数值

                // 3.将计算结果插入新链表尾部
                curr.next = new ListNode(num); // 创建新节点追加到原节点后面
                curr = curr.next; // 加到原节点后面

                // 循环走到最后
                p = p == null ? p : p.next;
                q = q == null ? q : q.next;
            }
            // 处理进位节点
            if(carry > 0) {
                curr.next = new ListNode(carry);
            }

            return resultHead.next; // 返回第一个节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
