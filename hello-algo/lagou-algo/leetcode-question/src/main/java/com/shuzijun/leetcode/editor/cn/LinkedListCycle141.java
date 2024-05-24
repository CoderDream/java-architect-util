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


//import com.shuzijun.leetcode.editor.cn.LinkedListCycle141.Solution.ListNode;

/**
 * @author CoderDream
 */
public class LinkedListCycle141 {

    public static void main(String[] args) {
        Solution solution = new LinkedListCycle141().new Solution();

//        ListNode listNode = new ListNode();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     * </pre>
     */
    public class Solution {

        /**
         * <pre>
         * 解法一：二次到达解法
         *  1.定义数组记录一访问节点
         *      new ListNode[10000];
         *  2.遍历链表的每个节点，并与容器中已存放的节点依次比较；
         *      相同则方法结束，返回true
         *      不同则存入最新位置，继续遍历下一个节点
         *  3.若next指针为null，则方法结束，返回false
         *
         * </pre>
         *
         * @param head
         * @return
         */
        public boolean hasCycle(ListNode head) {
            //1.定义数组记录一访问节点
            ListNode[] array = new ListNode[10000];
            //2.遍历链表的每个节点，并与容器中已存放的节点依次比较；
            while (head != null) {
                // 并与容器中已存在的节点依次比较
                for (int i = 0; i < array.length; i++) {
                    //    相同则方法结束，返回true
                    if (array[i] == head) {
                        return true;
                    }
                    if (array[i] == null) {
                        array[i] = head; // 将当前节点存放到最新的位置
                        break; // 结束容器的遍历
                    }
                }

                //    不同则存入最新位置，继续遍历下一个节点
                head = head.next;
            }

            //3.若next指针为null，则方法结束，返回false
            return false;
        }

//        class ListNode {
//
//            int val;
//            ListNode next;
//
//            ListNode(int x) {
//                val = x;
//                next = null;
//            }
//        }
    }

//leetcode submit region end(Prohibit modification and deletion)


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
