package com.debug.middleware.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 二倍均值法的代码实战
 * @author: zhonglinsen
 * @date: 2019/3/15
 */
public class RedPacketUtil {

    /**
     * 发红包算法，金额参数以分为单位
     * @param totalAmount
     * @param totalPeopleNum
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();

        if (totalAmount>0 && totalPeopleNum>0){
            Integer restAmount = totalAmount;
            Integer restPeopleNum = totalPeopleNum;

            Random random = new Random();
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                // 随机范围：[1，剩余人均金额的两倍)，左闭右开

                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                restAmount -= amount;
                restPeopleNum--;
                amountList.add(amount);
            }
            amountList.add(restAmount);
        }

        return amountList;
    }

}
