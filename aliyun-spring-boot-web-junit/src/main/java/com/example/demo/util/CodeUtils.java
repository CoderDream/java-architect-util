package com.example.demo.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CodeUtils {

    private static final String[] NUMBER_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] LETTER_ARRAY = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final String[] CHAR_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
//        System.out.println(getNextChar("0", true));
//        System.out.println(getNextChar("0", false));
//        System.out.println(getNextChar("9", true));
//        System.out.println(getNextChar("9", false));
//        System.out.println(getNextChar("Z", true));
//        System.out.println(getNextChar("Z", false));


//        System.out.println(genNextCode("XY", "MM"));
        System.out.println(genNextCode("ZZ11", "MMMM"));
    }

    /**
     * 获取下一个字符
     *
     * @param str 输入值
     * @return 下一个字符
     */
    public static String getNextChar(String str) {
        List<String> totalList = Arrays.asList(CHAR_ARRAY);
        int listSize = totalList.size();
        int index = totalList.indexOf(str);
        // 如果是最后一位
        if (index == listSize - 1) {
            // 可以借位，返回第一个字符
            return totalList.get(0);
        }
        // 正常操作
        else {
            return totalList.get(index + 1);
        }
    }

    /**
     * 获取下一个字符
     *
     * @param str 输入值
     * @return 下一个字符
     */
    public static String getNextNumber(String str) {
        List<String> totalList = Arrays.asList(CHAR_ARRAY);
        int listSize = totalList.size();
        int index = totalList.indexOf(str);
        // 如果是最后一位，返回第一个字符
        if (index == listSize - 1) {
            return totalList.get(0);
        }
        // 正常操作
        else {
            return totalList.get(index + 1);
        }
    }

    /**
     * 获取下一个字符
     *
     * @param str       输入值
     * @param carryFlag 可以进位
     * @return 下一个字符
     */
    public static String getNextChar(String str, Boolean carryFlag) {
        List<String> totalList = Arrays.asList(CHAR_ARRAY);
        int listSize = totalList.size();
        int index = totalList.indexOf(str);
        // 如果是最后一位
        if (index == listSize - 1) {
            // 可以借位，返回第一个字符
            if (carryFlag) {
                return totalList.get(0);
            }
            // 不能借位，已经是最后一位了，返回空串
            else {
                return "";
            }
        }
        // 正常操作
        else {
            return totalList.get(index + 1);
        }
    }

    /**
     * 获取下一个字符
     *
     * @param format   输入值
     * @param lastCode 可以进位
     * @return 下一个字符
     */
    public static String genNextCode(String lastCode, String format) {

        if (StringUtils.isEmpty(lastCode) || StringUtils.isEmpty(format)) {
            return "";
        }

        List<String> list = Arrays.asList(lastCode.split(""));
        // List集合的逆序排列
        Collections.reverse(list);
        if (list.size() != format.length()) {
            return "";
        }

        List<String> totalList = Arrays.asList(CHAR_ARRAY);

        // 总字符数
        int listSize = totalList.size();
        int i = 0;
        // 先取第一位的后一个编码
        String nextChar = getNextChar(list.get(i));
        list.set(i, nextChar);
        // 如果是0，必须进位
        while ("0".equals(nextChar)) {
            // 设置第一个元素
            list.set(i, nextChar);
            //
            i++;
            if (i == format.length()) {
                break;
            }
            nextChar = getNextChar(list.get(i));
            if (i == format.length() - 1 && "0".equals(nextChar)) {
                return "";
            }
        }

        // List集合的逆序排列，转回来
        Collections.reverse(list);
        String code = list.stream().collect(Collectors.joining(""));

        return code;
    }

    /**
     * 初始值
     *
     * @param format 格式
     * @return 下一个字符
     */
    public static String genStartCode(String format) {

        if (StringUtils.isEmpty(format)) {
            return "";
        }

        List<String> list = Arrays.asList(format.split(""));
        List<String> newList = new ArrayList<>();

        int listSize = 0;
        if (list != null) {
            listSize = list.size();
        }
        for (int i = 0; i < listSize; i++) {
            if (i == listSize - 1) {
                newList.add("1");
                break;
            } else {
                newList.add("0");
            }
        }

        // List转字符串
        return newList.stream().collect(Collectors.joining(""));
    }
}
