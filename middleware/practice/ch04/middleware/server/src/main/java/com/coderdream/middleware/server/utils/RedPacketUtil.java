package com.coderdream.middleware.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 二倍均值法的代码实战
 *
 * @author CoderDream
 */
public class RedPacketUtil {

    /**
     * 发红包算法，金额参数以分为单位
     *
     * @param totalAmount 红包总金额（单位为分）
     * @param totalPeopleNum 总人数
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        // 用于存储每次产生的小红包随机金额列表，金额单位为分
        List<Integer> amountList = new ArrayList<>();
        // 判断总金额和总人数参数的合法性
        if (totalAmount > 0 && totalPeopleNum > 0) {
            // 记录剩余的总金额，初始化时即为红包的总金额
            Integer restAmount = totalAmount;
            // 记录剩余的总人数，初始化时即为红包的总人数
            Integer restPeopleNum = totalPeopleNum;
            // 定义产生随机数的实例对象
            Random random = new Random();
            // 不断循环遍历、迭代更新地产生随机金额，直到 N-1 ≤ 0
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                // 随机范围：[1，剩余人均金额的两倍)，左闭右开，amount 即为产生的随机金额 R，单位为分
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                // 更新剩余的总金额 M = M - R
                restAmount -= amount;
                // 更新剩余的总人数 N = N - 1
                restPeopleNum--;
                // 将产生的随机金额添加进列表中
                amountList.add(amount);
            }
            // 循环完毕，剩余的金额即为最后一个随机金额，也需要将其添加进列表中
            amountList.add(restAmount);
        }
        // 将最终产生的随机金额列表返回
        return amountList;
    }

}
