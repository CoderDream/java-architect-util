package com.coderdream.util;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class ComparatorDemo {
    public static void main(String[] args) {

        List<User> users = initUsers();
        String[] properties =   {"age","birthday"};
        multiSort(users, properties);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (int i = 0; i < 10; i++) {
            User user = users.get(i);
            System.out.println(user.getName() + "---" + user.getSex() + "---" + user.getAge() + "---" + user.getBirthday());
        }
    }

    /**
     *
     * @param users
     */
    private static void multiSort(List<User> users, String[] properties) {
        Comparator mycmp1 = ComparableComparator.getInstance();
        mycmp1 = ComparatorUtils.reversedComparator(mycmp1); //逆序

        Comparator mycmp2 = ComparableComparator.getInstance();
//		mycmp2 = ComparatorUtils. reversedComparator(mycmp2); //允许null
        mycmp2 = ComparatorUtils.nullHighComparator(mycmp2); //允许null

        // 声明要排序的对象的属性，并指明所使用的排序规则，如果不指明，则用默认排序
        ArrayList<Object> sortFields = new ArrayList<Object>();
        sortFields.add(new BeanComparator(properties[0], mycmp1)); //主排序（第一排序）
        sortFields.add(new BeanComparator(properties[1], mycmp2)); //次排序（第二排序）

        // 创建一个排序链
        ComparatorChain multiSort = new ComparatorChain(sortFields);

        // 开始真正的排序，按照先主，后副的规则
        Collections.sort(users, multiSort);
    }

    private static List<User> initUsers() {
        List<User> list = new ArrayList<User>();
        for (Integer i = 0; i < 10; i++) {
            Random rm = new Random();
            User user = new User();
            user.setName("user" + i);
            user.setAge(rm.nextInt(10));
            if (i % 2 == 0) {
                user.setSex("1");
            } else {
                user.setSex("0");
            }
            user.setBirthday(new Date(2014, 11, 21));
            if (i == 5) {
                user.setBirthday(new Date(2014, 11, 20));
                user.setSex(null);
            }
            System.out.println(user.getName() + "---" + user.getSex() + "---" + user.getAge() + "---" + user.getBirthday());
            list.add(user);
        }
        return list;
    }
}
