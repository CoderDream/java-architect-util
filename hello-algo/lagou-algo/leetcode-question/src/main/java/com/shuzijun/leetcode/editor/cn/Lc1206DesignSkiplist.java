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
public class Lc1206DesignSkiplist {

    public static void main(String[] args) {
        Skiplist skiplist = new Lc1206DesignSkiplist().new Skiplist();

//            Skiplist skiplist = new Skiplist();
        skiplist.add(30);
        skiplist.add(40);
        skiplist.add(50);
        skiplist.add(60);
        skiplist.add(70);
        skiplist.add(90);

        System.out.println(skiplist.search(30)); // true
        System.out.println(skiplist.search(45)); // false
        skiplist.add(45);
        System.out.println(skiplist.search(45)); // true
        skiplist.erase(45);
        System.out.println(skiplist.search(45)); // false

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Skiplist {

        static final int MAX_LEVEL = 16; // 定义跳表的最大层数
        private final Node head = new Node(-1, MAX_LEVEL); // 初始化头节点，值为-1，层数为最大层数
        private int level = 0; // 当前跳表的层数
        private final Random random = new Random(); // 随机数生成器

        // Node类表示跳表中的节点
        class Node {
            int value; // 节点的值
            Node[] forward; // 前进指针数组，不同层级的前进指针

            Node(int value, int level) {
                this.value = value; // 初始化节点值
                this.forward = new Node[level + 1]; // 初始化前进指针数组
            }
        }

        // 生成节点的随机层数
        private int randomLevel() {
            int lvl = 0;
            // 当随机数小于0.5且层数小于最大层数时，增加层数
            while (random.nextFloat() < 0.5f && lvl < MAX_LEVEL) {
                lvl++;
            }
            return lvl; // 返回生成的层数
        }

        // 在跳表中查找目标值
        public boolean search(int target) {
            Node current = head; // 从头节点开始
            // 从最高层逐层向下查找
            for (int i = level; i >= 0; i--) {
                // 向前移动直到找到大于或等于目标值的节点
                while (current.forward[i] != null && current.forward[i].value < target) {
                    current = current.forward[i];
                }
            }
            current = current.forward[0]; // 移动到最底层
            // 检查当前节点的值是否等于目标值
            return current != null && current.value == target;
        }

        // 向跳表中插入一个值
        public void add(int num) {
            Node[] update = new Node[MAX_LEVEL + 1]; // 用于存储需要更新的节点
            Node current = head; // 从头节点开始

            // 从最高层逐层向下查找
            for (int i = level; i >= 0; i--) {
                // 向前移动直到找到大于或等于插入值的节点
                while (current.forward[i] != null && current.forward[i].value < num) {
                    current = current.forward[i];
                }
                update[i] = current; // 存储需要更新的节点
            }

            int lvl = randomLevel(); // 生成新节点的随机层数
            if (lvl > level) {
                // 如果新节点的层数大于当前层数，更新头节点的前进指针
                for (int i = level + 1; i <= lvl; i++) {
                    update[i] = head;
                }
                level = lvl; // 更新当前层数
            }

            Node newNode = new Node(num, lvl); // 创建新节点
            for (int i = 0; i <= lvl; i++) {
                newNode.forward[i] = update[i].forward[i]; // 设置新节点的前进指针
                update[i].forward[i] = newNode; // 更新前一个节点的前进指针
            }
        }

        // 从跳表中删除一个值
        public boolean erase(int num) {
            Node[] update = new Node[MAX_LEVEL + 1]; // 用于存储需要更新的节点
            Node current = head; // 从头节点开始

            // 从最高层逐层向下查找
            for (int i = level; i >= 0; i--) {
                // 向前移动直到找到大于或等于删除值的节点
                while (current.forward[i] != null && current.forward[i].value < num) {
                    current = current.forward[i];
                }
                update[i] = current; // 存储需要更新的节点
            }

            current = current.forward[0]; // 移动到最底层
            // 检查当前节点的值是否等于删除值
            if (current != null && current.value == num) {
                // 更新前进指针，跳过当前节点
                for (int i = 0; i <= level; i++) {
                    if (update[i].forward[i] != current) {
                        break;
                    }
                    update[i].forward[i] = current.forward[i];
                }

                // 如果最高层的节点为空，降低当前层数
                while (level > 0 && head.forward[level] == null) {
                    level--;
                }
                return true; // 返回true表示删除成功
            }
            return false; // 返回false表示删除失败
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
