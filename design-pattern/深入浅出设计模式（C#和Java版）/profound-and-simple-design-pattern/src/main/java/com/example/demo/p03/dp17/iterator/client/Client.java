package com.example.demo.p03.dp17.iterator.client;

import com.example.demo.p03.dp17.iterator.bean.IntSet;

import java.util.Enumeration;

/**
 * 迭代器的应用测测试
 */
public class Client {
    public static void main(String[] args) {
        IntSet set = new IntSet();
        //增加成员
        for (int i = 2; i < 10; i += 2) {
            set.add(i);
        }
        //检查一下成员
        System.out.println("聚集成员检查");
        for (int i = 1; i < 9; i++) {
            System.out.print(i + "-" + set.isMember(i) + " ");
        }
        //客户请求collection对象生成多个迭代器
        IntSet.Iterator it1 = set.createIterator();
        IntSet.Iterator it2 = set.createIterator();
        //客户使用first(), isDone(), next(), currentItem()方法
        System.out.print("\nIterator: ");
        for (it1.first(), it2.first(); !it1.isDone(); it1.next(), it2.next()) {
            System.out.print(it1.currentItem() + " " + it2.currentItem() + " ");
        }
        //Java习惯使用一个聚集历遍器,称为Enumeration
        System.out.print("\nEnumeration: ");
        for (Enumeration e = set.getHashtable().keys(); e.hasMoreElements(); ) {
            System.out.print(e.nextElement() + " ");
        }
        System.out.println();
    }
}
