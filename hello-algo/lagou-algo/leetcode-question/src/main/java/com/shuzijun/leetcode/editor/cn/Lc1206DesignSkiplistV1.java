//不使用任何库函数，设计一个 跳表 。
//
// 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思
//想与链表相似。
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
//
//
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。
//
// 了解更多 : https://oi-wiki.org/ds/skiplist/
//
// 在本题中，你的设计应该要包含这些函数：
//
//
// bool search(int target) : 返回target是否存在于跳表中。
// void add(int num): 插入一个元素到跳表。
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
//
//
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
//
//
//
// 示例 1:
//
//
//输入
//["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase",
// "search"]
//[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
//输出
//[null, null, null, null, false, null, true, false, true, false]
//
//解释
//Skiplist skiplist = new Skiplist();
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
//
//
//
//
// 提示:
//
//
// 0 <= num, target <= 2 * 10⁴
// 调用search, add, erase操作次数不大于 5 * 10⁴
//
//
// Related Topics 设计 链表 👍 278 👎 0


package com.shuzijun.leetcode.editor.cn;

import java.util.Random;

/**
 * @author CoderDream
 */
public class Lc1206DesignSkiplistV1 {

    public static void main(String[] args) {
        Skiplist skiplist = new Lc1206DesignSkiplistV1().new Skiplist();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Skiplist {

        final int HEAD_VALUE = -1; // 链表头节点的值
        final Node HEAD = new Node(HEAD_VALUE);

        Node head;  // 最左上角的头节点，所有操作在开始位置
        int levels; // 当前层级，即 head 节点所在的最高层数
        int length; // 链表长度

        public Skiplist() {
            head = HEAD;
            levels = 1;
            length = 1;
        }

        public Node get(int target) {
            return get(target, head);
        }

        public Node get(int target, Node from) {
            Node n = from;
            while (n != null) {
                // 1.在同一层级上向右查找，直到链接结尾，或者找到
                while (n.right != null && n.right.val < target) {
                    n = n.right;
                }
                // 2.若找到，返回true
                Node right = n.right; // 要查找的节点
                if (right != null && right.val == target) {
                    return n; // 返回要查找的节点的前一个
                }
                // 3.若右侧数据较大，向下一层
                n = n.down;
            }
            return null;
        }

        /**
         * <pre>
         * 从 head 开始，从左到右、从上到下依次查找
         *  1.小于，往右
         *  2.相同，则返回
         *  3.链表结尾，或大于，往下
         *
         * </pre>
         *
         * @param target
         * @return
         */
        public boolean search(int target) {
//            Node n = head;
//            while (n != null) {
//                // 1.在同一层级向右查找，直到链表的结尾
//                while (n.right != null && n.right.val < target) {
//                    n = n.right; // 向右
//                }
//                // 2.若找到，返回true
//                Node right = n.right; // 要查找的节点
//                if(right != null && right.val == target) {
//                    return true;
//                }
//
//                // 3.若右侧数据较大，向下一层
//                n = n.down; // 往下
//            }
//
//            return false;
            Node node = get(target);
            return node != null;
        }

        /**
         * <pre>
         * 插入节点，将节点插入原链表中正确的排序位置
         *  1.定位插入位置：原链表中 >= num 的最小节点前
         *  2.插入新节点
         *  3.根据扔硬币决定（是否）生成索引
         * </pre>
         *
         * @param num
         */
        public void add(int num) {
            // 1.定位插入位置：原链表中 >= num 的最小节点前
            Node node = head;
            Node[] nodes = new Node[levels]; // 层级
            int i = 0; // 操作上述数组
            while (node != null) { // node == null, 到达原链表（第一层）
                while (node.right != null && node.right.val < num) {
                    node = node.right; // 向右走
                }

                nodes[i++] = node;

                // 到达原链表（第一层）
//                if(node.down == null) {
//                    break;
//                }

                // 继续查找下一层的位置
                node = node.down;
            }

            // 2.插入新节点
            node = nodes[--i]; // nodes 中最后一个元素

            Node newNode = new Node(num, node.right, null);
            node.right = newNode;
            length++;

            // 3.根据扔硬币决定（是否）生成索引
            addIndicesByCoinFlip(newNode, nodes, i);
        }

        /**
         * 抛硬币
         *
         * @param target
         * @param nodes
         * @param indices 可以创建的层数
         */
        private void addIndicesByCoinFlip(Node target, Node[] nodes, int indices) {
            Node downNode = target;
            // 1.抛硬币，在现有层级范围内建立索引
            Random random = new Random();
            int coins = random.nextInt(2); // 0 or 1, 50%概率
            while (coins == 1 && levels < length >> 6) { // 除以2的2次方
                if (indices > 0) {
                    Node prev = nodes[--indices]; // 数组的倒数第二个元素，level 2，（循环下一次为 level 3）
                    Node newIndex = new Node(target.val, prev.right, downNode); // newIndex指向当前节点
                    prev.right = newIndex; //
                    //indices--;
                    downNode = newIndex; // 保存新的向下节点
                    coins = random.nextInt(2); // 0 or 1, 50%概率
                } else {
                    // 2.抛硬币，决定是否建立一层超出跳表层数的索引层
                    // 新建索引节点和头节点
                    Node newIndex = new Node(target.val, null, downNode); // 新层级，右边为null，下为上一次的down节点
                    Node newHead = new Node(HEAD_VALUE, newIndex, head); // 头节点
                    head = newHead; // head 指针上移
                    levels++; // 跳表层数加 1
                }
            }
        }

        /**
         * <pre>
         * 遍历跳表，查找与给定值相同的节点，删除每一层
         *  1.获取该指定数据节点的前一个节点
         *  2.与当前层链表断开
         *  3.下移，删除每一层
         * </pre>
         *
         * @param num
         * @return
         */
        public boolean erase(int num) {
            boolean exist = false;
//            Node n = head;
//            while (n != null) {
//                // 1.获取该指定数据节点的前一个节点
//                while (n.right != null && n.right.val < num) {
//                    n = n.right; // 向右
//                }
//                // 2.与当前层链表断开
//                Node right = n.right; // 要删除的节点
//                if(right != null && right.val == num) {
//                    n.right = right.right; // 前一节点 指向待删除节点的后一节点
//                    right.right = null; // help GC
//                    exist = true;
//                }
//
//                // 3.下移，删除每一层
//                // 删除下一层
//                n = n.down;
//            }
            Node node = get(num, head);
            while (node != null) {
                Node right = node.right; // 要删除的节点
                node.right = right.right;
                right.right = null; // help GC
                exist = true;

                node = get(num, node.down);
            }
            if (exist) {
                length--; // 链表长度减 1
            }

            return exist;
        }

        class Node {

            int val;
            Node right, down;

            Node(int val) {
                this(val, null, null);
            }

            Node(int val, Node right, Node down) {
                this.val = val;
                this.right = right;
                this.down = down;
            }
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//leetcode submit region end(Prohibit modification and deletion)


}
