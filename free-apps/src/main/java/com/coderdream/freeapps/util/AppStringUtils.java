package com.coderdream.freeapps.util;

public class AppStringUtils {

    public static void main(String[] args) {
        String priceStr = "¥30➱0";
        priceStr = "¥12➱0\t\u200E";
        String realPriceStr = AppStringUtils.filterPriceStr(priceStr);
        System.out.println(realPriceStr);
    }


    public static String filterPriceStr(String priceStr){
        String result = priceStr.replaceAll("➱0", "");
        result = result.replaceAll(",", "");
        result = result.replaceAll("➠0", "");
        result = result.replaceAll("➞", "");
        result = result.replaceAll("\t➞", "");
        result = result.replaceAll("\t0", "");
        result = result.replaceAll("=0", "");
        result = result.replaceAll("➱0", "");
        result = result.replaceAll("➞0", "");
        result = result.replaceAll("→0", "");
        result = result.replaceAll("➱ 0", "");
        result = result.replaceAll("→ 0", "");
        result = result.replaceAll(" 0", "");
        result = result.replaceAll("-0", "");
        result = result.replaceAll(" ", "");
        result = result.replaceAll("➱", "");
        result = result.replaceAll("\\.00", "");
        result = result.replaceAll("\\.0", "");
//        result = result.replaceAll("¥0", "");
        result = result.replaceAll("¥", "");
        result = result.replaceAll("\\$", "");
        result = result.replaceAll("￥", "");
        result = result.replaceAll("￥0", "");
        result = result.replaceAll("美元", "");
        result = result.replaceAll("元", "");
        result = result.replaceAll("元", "");
        result = result.replaceAll("免费", "");
        result = result.replaceAll("iPhone", "");
        result = result.replaceAll("【", "");
        result = result.replaceAll("】", "");
        result = result.replaceAll("➜", "");
        result = result.replaceAll("·", "");
        result = result.replaceAll(" ", "");
        result = result.replaceAll("iPad", "");

        return result;
    }
}
