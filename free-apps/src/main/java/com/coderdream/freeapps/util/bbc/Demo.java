package com.coderdream.freeapps.util.bbc;

import io.swagger.models.auth.In;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author CoderDream
 */
public class Demo {

    public static void main(String[] args) {

//        String first = "'jaali'";
//        if (first.startsWith("'") && first.endsWith("'")) {
//            first = first.substring(1, first.length() - 1);
//        }
//        System.out.println(first);
        String str = "hello world";
        String[] arr = str.split(str);
        System.out.println(arr);

//        List<Integer> integerList = Arrays.asList(13, 40, 22, 50, 52, 5, 12, 34, 43, 52, 3, 51, 54);

//        List<Integer> integerList = Arrays.asList(13, 40, 52, 54);
//        processInteger(integerList);

//        List<String> stringList = Arrays.asList("I’m Beth."
//            , "And I’m Neil."
//            , "If you really love something,"
//            , "maybe a sport or a hobby,"
//            , "a music band or a TV show,"
//            , "you might call yourself a ‘fan’."
//            , "Nowadays,"
//            , "thanks to the internet,"
//            , "fans from around the world can meet online to"
//            , "share their passion,"
//            , "and this has led to a new word:"
//            , "‘fandom’.");
//        List<String> result = processString(stringList);
//        for (String str : result) {
//            System.out.println(str.length() + ":" + str);
//        }

//        String str = "misophonia sufferers have developed some tricks  to help. ";
//        str = str.replaceAll("  ", " ");
//        System.out.println(str);


//        List<String> stringList = Arrays.asList("– ",            "- ");
//        for (String tempStr: stringList        ) {
//            m1(tempStr);
//        }
    }

    public static void m1( String s) {
//        String shang = "殇";
        String result = "";
        System.out.println(s);
//        try {
////            result = URLEncoder.encode(shang, "GBK");
////            System.out.println("GBK:" + result);
////
////            result = URLEncoder.encode(shang, "UTF-8");
////            System.out.println("UTF8: " + result);
////
////            result = URLEncoder.encode(shang, "UTF-16");
////            System.out.println("UTF16:" + result);
//        } catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//        }

        for(int i = 0; i < s.length(); i++){
            System.out.println((int)s.charAt(i));
        }
    }

    public static void processInteger(List<Integer> integerList) {
        int MAX = 55;
        // 如果只有一个元素，直接返回
        if (integerList.size() == 1) {
            System.out.println(integerList.get(0));
        } else {
            int sum;
            // 先将第一个元素保存到临时变量
            int temp = integerList.get(0);
            for (int i = 1; i < integerList.size(); i++) {
                // 累加循环得到的变量
                sum = temp + integerList.get(i);
                // 如果没有超过阈值，则继续
                if (sum < MAX) {
                    // 修改临时变量
                    temp = sum;
                } else {
                    // 已经超过阈值，则输出临时变量（累加结束）
                    System.out.println(temp);
                    // 将当前元素赋值给临时变量
                    temp = integerList.get(i);
                }
                // 如果没有元素了，则直接输出最后得到的结果
                if (i == integerList.size() - 1) {
                    System.out.println(temp);
                }
            }
        }
    }

    /**
     * 输出合并后的字符串列表，字符串长度不大于某个阈值
     * @param stringList 输入字符串列表
     * @return 输入字符串列表
     */
    public static List<String> processString(List<String> stringList) {
        List<String> result = new ArrayList<>();

        int MAX = 55;
        String tempString;
        // 如果只有一个元素，直接返回
        if (stringList.size() == 1) {
            System.out.println(stringList.get(0));
            tempString = stringList.get(0);
            result.add(tempString);
        } else {
            int sum;
            String sumStr;
            // 先将第一个元素保存到临时变量
            tempString = stringList.get(0);
            int temp = tempString.length();
            for (int i = 1; i < stringList.size(); i++) {
                // 累加循环得到的变量
                sum = temp + stringList.get(i).length();
                sumStr = tempString + stringList.get(i);
                // 如果没有超过阈值，则继续
                if (sum < MAX) {
                    // 修改临时变量
                    temp = sum;
                    tempString = sumStr;
                } else {
                    // 已经超过阈值，则输出临时变量（累加结束）
                    result.add(tempString);
                    // 将当前元素赋值给临时变量
                    temp = stringList.get(i).length();
                    tempString = stringList.get(i);
                }
                // 如果没有元素了，则直接输出最后得到的结果
                if (i == stringList.size() - 1) {
                    result.add(tempString);
                }
            }
        }

        return result;
    }
}
