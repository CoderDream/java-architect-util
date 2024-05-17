package com.coderdream.ch03.ch03_01;// 建立五位学生成绩的单向链表，
// 再遍历这个单向链表的每一个节点来打印输出学生的成绩

import java.io.*;

public class ch03_01 {

    public static void main(String args[]) throws IOException {
        BufferedReader buf;
        buf = new BufferedReader(new InputStreamReader(System.in));
        int num;
        String name;
        int score;

        System.out.println("请输入5位学生的数据： ");
        LinkedList list = new LinkedList();
//        for (int i = 1; i < 6; i++) {
//            System.out.print("请输入学号： ");
//            num = Integer.parseInt(buf.readLine());
//            System.out.print("请输入姓名： ");
//            name = buf.readLine();
//            System.out.print("请输入成绩： ");
//            score = Integer.parseInt(buf.readLine());
//            list.insert(num, name, score);
//            System.out.println("-------------");
//        }

		list.insert(1, "黄小华", 85);
		list.insert(2, "方小源", 95);
		list.insert(3, "林大晖", 68);
		list.insert(4, "孙阿毛", 72);
		list.insert(5, "王小明", 79);

        System.out.println(" 学 生 成 绩 ");
        System.out.println(" 学号  姓名 成绩 ===========");
        list.print();
    }
}
