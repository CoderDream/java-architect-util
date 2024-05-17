package com.coderdream.freeapps.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 等额本息计算工具类
 *
 * <p>
 * 等额本息还款，也称定期付息，即借款人每月按相等的金额偿还贷款本息，其中每月贷款利息按月初剩余贷款本金计算并逐月结清。把按揭贷款的本金总额与利息总额相加，
 * 然后平均分摊到还款期限的每个月中。作为还款人，每个月还给银行固定金额，但每月还款额中的本金比重逐月递增、利息比重逐月递减。
 *
 * @author CoderDream
 */
public class AverageCapitalPlusInterestUtils {

    /**
     * 每月偿还本金和利息
     * <p>
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,单位分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金和利息(入1 单位分)
     */
    public static double getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double perMonthPrincipalInterest =
            invest * (monthRate * Math.pow(1 + monthRate, totalMonth)) / (Math.pow(1 + monthRate, totalMonth) - 1);
        return new BigDecimal(perMonthPrincipalInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 等额本息的每月偿还利息
     * <p>
     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息(入1 单位分)
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<>();
        double monthRate = yearRate / 12;
        double monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            double multiply = invest * monthRate;
            double sub = Math.pow(1 + monthRate, totalMonth) - Math.pow(1 + monthRate, i - 1);
            monthInterest = multiply * sub / (Math.pow(1 + monthRate, totalMonth) - 1);
            map.put(i, new BigDecimal(monthInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return map;
    }

    /**
     * 等额本息的每月偿还本金（月还本息-月还利息）
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金(取整舍 单位分)
     */
    public static Map<Integer, Double> getPerMonthPrincipal(double invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double monthIncome = invest * monthRate * Math.pow(1 + monthRate, totalMonth)
            / (Math.pow(1 + monthRate, totalMonth) - 1);
        double perMonthPrincipalInterest = new BigDecimal(monthIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        Map<Integer, Double> mapPrincipal = new HashMap<>();
        double monthInterest;
        double temp;
        for (int i = 1; i < totalMonth + 1; i++) {
            Double multiply = invest * monthRate;
            double sub = (Math.pow(1 + monthRate, totalMonth)) - (Math.pow(1 + monthRate, i - 1));
            monthInterest = multiply * sub / (Math.pow(1 + monthRate, totalMonth) - 1);
            double monthInterestL = new BigDecimal(monthInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            temp = perMonthPrincipalInterest - monthInterestL;
            mapPrincipal.put(i, new BigDecimal(temp).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return mapPrincipal;
    }

    /**
     * 等额本息的总利息
     *
     * @param invest     总借款额（贷款本金）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 总利息 (单位分)
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
        double count = 0;
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = count + entry.getValue();
        }
        return count;
    }

    public static void main(String[] args) {
        double invest = 342259.46; // 本金
        int month = 174;
        double yearRate = 0.031; // 年利率
        double perMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
        System.out.println("等额本息---每月还款本息：" + perMonthPrincipalInterest);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("等额本息---每月还款利息：" + mapInterest);
        Map<Integer, Double> mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
        double count = getInterestCount(invest, yearRate, month);
        System.out.println("等额本息---总利息：" + count);

        for (int i = 1; i < month + 1; i++) {
            if (i < month) {
                System.out.println(
                    "等额本息---" + i + "月还款本金:" + mapPrincipal.get(i) + ",还款利息:" + mapInterest.get(i));
                invest = invest - mapPrincipal.get(i);
            } else {
                System.out.println(
                    "等额本息---" + i + "月还款本金:" + invest + ",还款利息:" + (perMonthPrincipalInterest - invest));
            }
        }
    }
}
