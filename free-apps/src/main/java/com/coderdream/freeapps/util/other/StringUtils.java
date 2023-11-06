package com.coderdream.freeapps.util.other;

public class StringUtils {


    /**
     * 获取 AppId
     * url：https://apps.apple.com/us/app/marple/id288689440?l=zh
     * 获取最后一个/id和？之间的数字，返回id+数字
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
     * @param str
     * @return
     */
    public static boolean existQuestionMark(String str) {

        if(str == null || str.trim().equals("")){
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
