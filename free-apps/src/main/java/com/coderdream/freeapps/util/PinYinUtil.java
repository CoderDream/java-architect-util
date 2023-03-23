package com.coderdream.freeapps.util;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 汉字转换为拼音
 *
 * @author zs
 */
@Slf4j
public class PinYinUtil {

    private static String reg = "[\\u4e00-\\u9fa5]+";
    /**
     * 测试main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(toFirstChar("测站基本信息（地图使用)")); //转为首字母大写
        System.out.println(toFirstChar("流域水系信息表"));
        System.out.println(toFirstChar("测站信息（包括经纬度）"));
    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese
     * @return
     */
    public static String toFirstChar(String chinese) {
        /*if(!chinese.matches(reg)){
            return chinese;
        }*/
        StringBuilder builder = new StringBuilder();
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    String[] tmp = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
                    if(ArrayUtil.isNotEmpty(tmp)){
                        builder.append(tmp[0].charAt(0));
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            } else {
               builder.append(newChar[i]);
            }
        }
        return builder.toString();
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese) {
        StringBuilder builder = new StringBuilder();
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    String[] tmp = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
                    if(ArrayUtil.isNotEmpty(tmp)){
                        builder.append(tmp[0]);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            } else {
                builder.append(newChar[i]);
            }
        }
        return builder.toString();
    }


}
