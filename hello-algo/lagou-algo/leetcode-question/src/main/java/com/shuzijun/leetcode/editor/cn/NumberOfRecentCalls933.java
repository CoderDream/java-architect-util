//写一个 RecentCounter 类来计算特定时间范围内最近的请求。
//
// 请你实现 RecentCounter 类：
//
//
// RecentCounter() 初始化计数器，请求数为 0 。
// int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求
//）。确切地说，返回在 [t-3000, t] 内发生的请求数。
//
//
// 保证 每次对 ping 的调用都使用比之前更大的 t 值。
//
//
//
// 示例 1：
//
//
//输入：
//["RecentCounter", "ping", "ping", "ping", "ping"]
//[[], [1], [100], [3001], [3002]]
//输出：
//[null, 1, 2, 3, 3]
//
//解释：
//RecentCounter recentCounter = new RecentCounter();
//recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
//recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
//recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
//recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回
//3
//
//
//
//
// 提示：
//
//
// 1 <= t <= 10⁹
// 保证每次对 ping 调用所使用的 t 值都 严格递增
// 至多调用 ping 方法 10⁴ 次
//
//
// Related Topics 设计 队列 数据流 👍 254 👎 0


package com.shuzijun.leetcode.editor.cn;

/**
 * @author CoderDream
 */
public class NumberOfRecentCalls933 {

    public static void main(String[] args) {
        RecentCounter solution = new NumberOfRecentCalls933().new RecentCounter();

        int[] arr = new int[]{1, 100, 3001, 3002};
        for (int j : arr) {
            System.out.println(solution.ping(j));
        }
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class RecentCounter {

        Queue q;

        public RecentCounter() {
            q = new Queue();
        }

        /**
         * <pre>
         * 最优解：队列解法
         *  1.使用链表实现一个队列
         *      定义属性：队头-head、队尾-tail、长度-size
         *      定义方法：添加节点-add(int)、移除节点-poll()、队列长度-size()
         *      定义内部类：Node，封装每次入队的请求数据和指向下一个节点的指针
         *  2.每次请求向队列尾部追加节点
         *  3.循环检查对头数据是否合法
         *      不合法则移除该节点
         *  4.返回队列长度
         *
         * </pre>
         *
         * @param t 时长为t（单位：毫秒）的请求
         * @return 过去3000毫秒内有多少次请求：[t-3000, t]
         */
        public int ping(int t) {
            // 2.每次请求向队列尾部追加节点
            q.add(t);

            // 3.循环检查对头数据是否合法（start往前走）
            while (q.head.getVal() < t - 3000) {
                q.poll();
            }

            // 不合法则移除该节点

            // 4.返回队列长度
            return q.size();
        }

        class Queue { // 1.使用链表实现一个队列

            Node head;
            Node tail;
            int size = 0;

            public Queue() {

            }

            public void add(int x) { // 向尾部添加一个节点
                Node last = tail; // 先获取尾部节点
                Node newNode = new Node(x);
                tail = newNode; // 尾指针指向新节点（操作队列）

                if (last == null) { //  第一次添加数据
                    head = newNode;
                    tail = newNode;
                } else {
                    last.next = newNode; // 前一个节点指向新节点（操作链表）
                }

                size++; // 每添加一个节点，队列长度+1
            }

            public int poll() { // 从头部移除一个节点
                int headVal = head.val; // 获取头结点的数据

                Node next = head.next; // 存储第一个节点
                head.next = null; // 链表第一个节点断开
                head = next; // head指针指向后一个节点（第一个节点）
                if (next == null) { // 队列中的最后一个元素
                    tail = null; // 处理尾指针
                }

                size--; // 每移出一个节点，队列长度减1
                return headVal; // 返回头指针的值
            }

            public int size() {
                return size;
            }

            class Node { // 队列节点：链表结构

                int val; // 数据
                Node next; // 指针域

                Node(int x) {
                    val = x;
                }

                int getVal() {
                    return val;
                }
            }
        }
    }

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)


}
