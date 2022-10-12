package com.example.demo.util;


import java.util.*;
import java.util.stream.Collectors;

public class CodeUtils {

    private static final String[] NUMBER_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] LETTER_ARRAY = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final String[] CHAR_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

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
     * 空字符串
     */
    private static final String NULL_STR = "";

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULL_STR.equals(str.trim());
    }

    public final static boolean isNull(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 获取下一个编码
     *
     * @param format   输入值
     * @param lastCode 可以进位
     * @return 下一个字符
     */
    public static String genNextCommonCode(String lastCode, String format) {

        if (isEmpty(lastCode) || isEmpty(format)) {
            return "";
        }

        List<String> list = Arrays.asList(lastCode.split(""));
        // List集合的逆序排列，0Z变Z0；
        Collections.reverse(list);
        if (list.size() != format.length()) {
            return "";
        }

        int codeSize = list.size();
        for (int i = 0; i < codeSize; i++) {
            // 先取第一位的后一个编码，如 Z -> 0
            String nextChar = getNextChar(list.get(i));
            list.set(i, nextChar);
            // 如果是0，而且已经处理到最后一个字符，则直接返回空
            if ("0".equals(nextChar) && codeSize == i + 1) {
                return "";
            }
            // 如果是0，必须进位，则继续处理下一位，否则退出
            if (!"0".equals(nextChar)) {
                break;
            }
        }

        // List集合的逆序排列，转回来
        Collections.reverse(list);
        String code = list.stream().collect(Collectors.joining(""));

        return code;
    }

    /**
     * 获取下一个公用编码
     *
     * @param commonFlag 输入值
     * @param lastCode   可以进位
     * @return 下一个字符
     */
    public static Result<String> genNextCode(String lastCode, Integer commonFlag) {
        Set<String> numberSet = new HashSet<>();
        Set<String> letterSet = new HashSet<>();
        numberSet.addAll(Arrays.asList(NUMBER_ARRAY));
        letterSet.addAll(Arrays.asList(LETTER_ARRAY));

        if (isEmpty(lastCode) || commonFlag == null) {
            return Result.error(500, "参数不合法！");
        }

        List<String> list = Arrays.asList(lastCode.split(""));

        // 如果是公共编码，但是第一位不是数字
        if (commonFlag == 1 && !numberSet.contains(list.get(0))) {
            return Result.error(500, "参数不合法：公共编码的第一位不是数字！");
        }
        // 如果是私有编码，但是第一位不是字母
        if (commonFlag == 0 && !letterSet.contains(list.get(0))) {
            return Result.error(500, "参数不合法：私有编码的第一位不是字母！");
        }

        // List集合的逆序排列，0Z变Z0；
        Collections.reverse(list);

        int codeSize = list.size();
        for (int i = 0; i < codeSize; i++) {
            // 先取第一位的后一个编码，如 Z -> 0
            String nextChar = getNextChar(list.get(i));
            list.set(i, nextChar);
            // 如果是A，而且已经处理到最后一个字符，则直接返回空，公共编码必须以数字开头
            if (codeSize == i + 1) {
                // 如果是公共编码，但是最后一位不是数字
                if (commonFlag == 1 && !numberSet.contains(nextChar)) {
                    return Result.error(501, "编码分配完毕：公共编码的第一位不是字母！");
                }
                // 如果是公共编码，但是最后一位不是数字
                if (commonFlag == 0 && !letterSet.contains(nextChar)) {
                    return Result.error(502, "编码分配完毕：私有编码的第一位不是数字！");
                }
            }
            // 如果是0，必须进位，则继续处理下一位，否则退出
            if (!"0".equals(nextChar)) {
                break;
            }
        }

        // List集合的逆序排列，转回来
        Collections.reverse(list);
        String code = list.stream().collect(Collectors.joining(""));

        return Result.ok("分配成功", code);
    }

    /**
     * 初始值
     *
     * @param format 格式
     * @return 下一个字符
     */
    public static String genStartCode(String format) {

        if (isEmpty(format)) {
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

    /**
     * 初始值
     *
     * @param format     格式
     * @param commonFlag 公共标识，默认1公共，0私有
     * @return 下一个字符
     */
    public static String genStartCode(String format, Integer commonFlag) {
        // 如果为空，直接返回
        if (isEmpty(format)) {
            return "";
        }

        if (commonFlag == null) {
            return "";
        }

        List<String> list = Arrays.asList(format.split(""));
        List<String> newList = new ArrayList<>();

        int listSize = 0;
        if (list != null) {
            listSize = list.size();
        }
        for (int i = 0; i < listSize; i++) {
            if (i == 0) {
                // 若为公共属性类型，则首位编码只能为数字；若非公共属性类型，则首位只能是字母。
                if (commonFlag == 1) {
                    newList.add("0");
                } else {
                    newList.add("A");
                }
            } else if (i == listSize - 1) {
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