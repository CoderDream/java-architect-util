package com.coderdream.freeapps.util.bbc;

/**
 * @author CoderDream https://blog.csdn.net/expect521/article/details/107107802
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 功能：拆分句子 详情：根据分隔符拆分句子，并且保留分隔符。拆分后的句子限制字数是10以内
 * <p>
 * 拆分规则： 分隔符(英文4个，中文4个) ,.?!，。？！ 最大字数限制(含标点)：10 字数若超过限制，则在前一个分隔符切分。若前面没有分隔符，则在下一个分隔符切分
 * <p>
 * 句子案例： 您好，请问可以听见吗？ 不好意思，这个我不会哎！ 很抱歉打扰到您了，祝您生活愉快，再见。
 * 据报道，刘德华（华仔）日前为庆祝官方粉丝后援会华仔天地32周年，特地举办线上聚会，不仅献唱5首歌，还罕见谈到妻子和孩子的近况，相当宠粉，他也自嘲过去1年因生病和疫情影响巡演取消，发现自己眼角多了皱纹还长出白发，近日，他在后援会晒出近照，真实状态也曝光了。
 */
public class RollbackToCompleteUtil {

    /**
     * 句子结束符.
     */
//    private static final String SEPARATOR_REGEX = "[,.?!，。？！]";
    private static final String SEPARATOR_REGEX = "[,.?!，。？！]";

    /**
     * 字数限制(包含标点).
     */
    private static final Integer SIZE = 60;

    public static void main(String[] args) {
        //1. 需要拆分的句子
        String sentence = "And I’m Beth. As the world switches from fossil fuels to renewable sources of energy, solar panels are appearing in more and more places… and with good reason.";
        sentence = "Great! But first I have a question for you, Beth.  Harnessing the power of the sun is not new, in fact solar power dates back over 2,700 years. In 213 BC, mirrors were used to reflect sunlight back onto Roman ships attacking the city of Syracuse, causing them to catch fire. But which ancient Greek philosopher was responsible for this solar heat ray? Was it:";
        System.out.println("原句子：" + sentence);

        List<String> stringList = splitSentence(sentence);
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("#" + i + ": " + stringList.get(i));
        }

//        //2. 需要分割的文本
//        String[] words = splitSentence(sentence);
//
//        //3. 输出 words
//        System.out.println("\n拆分后的句子：");
//        Arrays.stream(words).forEach(System.out::println);
//
//        //4. 限制拆分后的句子的字数在10位以内
//        System.out.println("\n正在处理每个句子允许的字数是10以内中.....");
//        List<String> wordList = limitNumber(words);
//
//        //5. 输出限制后的数据
//        System.out.println("\n最终处理完后的数据是： ");
//        wordList.stream().forEach(word -> {
//            System.out.println("word = " + word + "----------->length = " + word.length());
//        });
    }

    /**
     * 通过分隔符拆分句子,并保留分隔符
     * <p>
     * 例如：很抱歉打扰到您了，祝您生活愉快，再见。 根据分隔符拆分： 很抱歉打扰到您了， 祝您生活愉快， 再见。
     *
     * @return
     */
    public static List<String> splitSentence(String sentence) {
        Set<String> regexSet = new TreeSet<>();
        regexSet.add("!");
        regexSet.add("?");
        regexSet.add(".");
        regexSet.add("–");
        regexSet.add("…");

        List<String> result = new ArrayList<>();

        int startIndex = 0;
        String sentenceTemp;
        for (int i = 0; i < sentence.length(); i++) {
            String substring = sentence.substring(i, i + 1);
            // 找到了，开始分割并记录
            if (regexSet.contains(substring)) {
                sentenceTemp = sentence.substring(startIndex, i + 1);
                sentenceTemp = sentenceTemp.trim();
                System.out.println(sentenceTemp);
                if (sentenceTemp.length() > 80) {
                    String[] arr = sentenceTemp.split(", ");
                    for (int j = 0; j < arr.length; j++) {
                        // 不处理，直接放入
                        if (arr[j].length() > 240) {
                            result.add(arr[j]);
                        } else if (arr[j].length() > 160 && arr[j].length() <= 240) {
                            if (j < arr.length - 1) {
                                arr[j] = arr[j] + ", ";
                            }

                            int spaceIndex = 0;
                            // 寻找空格
                            for (int k = 70; k < 90; k++) {
                                char c = arr[j].charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    spaceIndex = k;
                                    break;
                                }
                            }

                            if (spaceIndex == 0) {
                                System.out.println("ERROR #6");
                                result.add(arr[j]);
                            }

                            result.add(arr[j].substring(0, spaceIndex + 1));

                            String secondStr = arr[j].substring(spaceIndex + 1);
                            int secondSpaceIndex = 0;
                            // 寻找空格
                            for (int k = 70; k < 90; k++) {
                                char c = secondStr.charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    secondSpaceIndex = k;
                                    break;
                                }
                            }
                            result.add(secondStr.substring(0, secondSpaceIndex + 1));
                            result.add(secondStr.substring(secondSpaceIndex + 1));
                        } else if (arr[j].length() > 80 && arr[j].length() <= 160) {
                            if (j < arr.length - 1) {
                                arr[j] = arr[j] + ", ";
                            }

                            int spaceIndex = 0;
                            // 寻找空格
                            for (int k = 70; k < 90; k++) {
                                char c = arr[j].charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    spaceIndex = k;
                                    break;
                                }
                            }

                            if (spaceIndex == 0) {
                                System.out.println("ERROR #7");
                                result.add(arr[j]);
                            }

                            result.add(arr[j].substring(0, spaceIndex + 1));
                            result.add(arr[j].substring(spaceIndex + 1));
                        } else {
                            if (j == arr.length - 1) {
                                result.add(arr[j]);
                            } else {
                                result.add(arr[j] + ", ");
                            }
                        }
                    }
                } else {
                    result.add(sentenceTemp);
                }

                startIndex = i + 1;
            }
        }

        return result;
    }

    /**
     * 限制拆分后的句子的字数包括分隔符在10位以内
     * <p>
     * 例如： 句子：很抱歉打扰到您了，祝您生活愉快，再见。 根据分隔符拆分后： 很抱歉打扰到您了， 祝您生活愉快， 再见。 限制字数后： 很抱歉打扰到您了， 祝您生活愉快，再见。
     *
     * @param words
     * @return
     */
    public static List limitNumber(String[] words) {
        //1. 存储限制字数的数据
        List<String> wordList = new ArrayList<>();

        //2. 限制字数在10以内
        int wordsLength = words.length;
        for (int i = 0; i < wordsLength; i++) {
            // 循环获取拆分后的每个句子
            String word = words[i];

            // 每个句子的长度
            int length = word.length();
            System.out.println("word = " + word + "-------------->length = " + length);

            // 当字数>=SIZE，直接存储到wordList
            if (length >= SIZE) {
                wordList.add(word);
            } else {
                // 防止ArrayIndexOutOfBoundsException
                if (i + 1 >= wordsLength) {
                    wordList.add(word);
                    return wordList;
                }

                // 获取下一个句子的长度
                String nextWord = words[i + 1];
                int nextLength = nextWord.length();

                // 如果上一个句子的长度 + 下一个句子的长度 <= SIZE,那么拼接成一个句子
                int totalLength = length + nextLength;
                if (totalLength <= SIZE) {
                    wordList.add(word + nextWord);
                    i++;
                } else {
                    wordList.add(word);
                }
            }
        }
        return wordList;
    }
}

