//给你一个链表的头节点 head ，判断链表中是否有环。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
//
// 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
// 示例 3：
//
//
//
//
//输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 10⁴]
// -10⁵ <= Node.val <= 10⁵
// pos 为 -1 或者链表中的一个 有效索引 。
//
//
//
//
// 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
//
// Related Topics 哈希表 链表 双指针 👍 2144 👎 0


package com.shuzijun.leetcode.editor.cn;

/**
 * @author CoderDream
 */
public class Lc141LinkedListCycle {

    public static void main(String[] args) {
        Solution solution = new Lc141LinkedListCycle().new Solution();
        ListNode l1 = new Lc141LinkedListCycle().new ListNode(3);
        ListNode l2 = new Lc141LinkedListCycle().new ListNode(2);
        ListNode l3 = new Lc141LinkedListCycle().new ListNode(0);
        ListNode l4 = new Lc141LinkedListCycle().new ListNode(-4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l2;

        Boolean result = solution.hasCycle(l1);
        System.out.println("result: " + result);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list. class ListNode { int val; ListNode next; ListNode(int x) { val = x; next =
     * null; } }
     */
    public class Solution {

        /**
         * <pre>
         * 解法二：快慢指针解法
         *  1.定义快慢两个指针
         *      slow=head; fast=head.next;
         *  2.遍历链表
         *      快指针步长为2：fast = fast.next.next;
         *      慢指针步长为1：slow = slow.next;
         *  3.当且仅当快慢指针重合，有环，返回true
         *  4.快指针为null，或其next指向null，没有环，返回false，操作结束
         *
         * </pre>
         *
         * @param head
         * @return
         */
        public boolean hasCycle(ListNode head) {
            if (head == null) { // 链表中有节点[0, 10^4]个
                return false;
            }

            // 1.定义快慢两个指针
            ListNode slow = head;
            ListNode fast = head.next;

            // 2.遍历链表
            while (fast != null && fast.next != null) {
                // 3.当且仅当快慢指针重合，有环，返回true
                if (slow == fast) { // 重合比较，指针指向同一个地址，所以可以用等号
                    return true;
                }

                // 迭代
                fast = fast.next.next; // 快指针步长为2：fast = fast.next.next;
                slow = slow.next; // 慢指针步长为1：slow = slow.next;
            } // 4.快指针为null，或其next指向null，没有环，返回false，操作结束

            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
