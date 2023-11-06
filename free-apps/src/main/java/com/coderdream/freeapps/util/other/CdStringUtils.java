package com.coderdream.freeapps.util.other;

import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.PriceHistory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CdStringUtils {

    /**
     * 获取 AppId url：https://apps.apple.com/us/app/marple/id288689440?l=zh 获取最后一个/id和？之间的数字，返回id+数字
     *
     * @param url 链接
     * @return 应用ID
     */
    public static String parseAppId(String url) {
        if (-1 == url.lastIndexOf("https")) {
            return "";
        }

        Integer indexId = url.lastIndexOf("/id");
        Integer indexQ = url.lastIndexOf("?");
        String id = "";
        if (indexQ != -1) {
            id = url.substring(indexId + 1, indexQ);
        } else {
            id = url.substring(indexId + 1);
        }
        return id;
    }

    /**
     * 是否存在问号
     *
     * @param str
     * @return
     */
    public static boolean existQuestionMark(String str) {

        if (str == null || str.trim().equals("")) {
            return false;
        }
// ?号匹配
        System.out.println(str.length());
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '?') {
//                System.out.println("####");
//                return false;
            } else {
                System.out.println("#####");
                return true;
            }
        }

        return false;
    }

    public static Integer genPrice(AppEntity app) {
        Double doublePrice = new Double(0);
        Integer usFlag = app.getUsFlag();
        if (usFlag !=null ) {
            if(usFlag == 0) {
                if(app.getPriceCn() != null) {
                    return app.getPriceCn();
                }
            } else {
                if(app.getPriceUs() != null) {
                    doublePrice = app.getPriceUs().doubleValue() * 6;
                }
            }
        }

        return (int) Math.round(doublePrice);
    }

    public static Integer genHistoryPrice(PriceHistory priceHistory) {
        Double doublePrice = (double) 0;
        Integer usFlag = priceHistory.getUsFlag();
        if (usFlag !=null ) {
            if(usFlag == 0) {
                if(priceHistory.getPriceCn() != null) {
                    return priceHistory.getPriceCn();
                }
            } else {
                if(priceHistory.getPriceUs() != null) {
                    doublePrice = priceHistory.getPriceUs().doubleValue() * 6;
                }
            }
        }

        return (int) Math.round(doublePrice);
    }


    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     */
    public static boolean isContainChinese(String str)  {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        if(StrUtil.isEmpty(str)) {
            return false;
        }
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    //

    public static void main(String[] args) {

        String s = "https://apps.apple.com/us/app/inotification/id1502455581";
        System.out.println(existQuestionMark(s));
        s = "https://apps.apple.com/us/app/inotification/id1502455581?abc";
        System.out.println(existQuestionMark(s));
        s = "???";
        System.out.println(existQuestionMark(s));
        s = "a???";
        System.out.println(existQuestionMark(s));
    }

}
