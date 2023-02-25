package com.coderdream.autogenvedio.util;

import cn.hutool.core.util.StrUtil;

public class AppStringUtils {


    public static String filterPriceStr(String priceStr){
        String result = priceStr.replaceAll("➱0", "");
        result = result.replaceAll("→0", "");
        result = result.replaceAll("→0", "");
        result = result.replaceAll("➱ 0", "");
        result = result.replaceAll("→ 0", "");
        result = result.replaceAll(" 0", "");
        result = result.replaceAll(" ", "");
        result = result.replaceAll("¥", "");
        result = result.replaceAll("￥", "");
        result = result.replaceAll("元", "");
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
