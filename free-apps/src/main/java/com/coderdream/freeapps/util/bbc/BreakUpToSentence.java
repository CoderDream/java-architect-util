package com.coderdream.freeapps.util.bbc;

/**
 * @author CoderDream https://blog.csdn.net/expect521/article/details/PRE_SIZE7PRE_SIZE7802
 */

import static com.coderdream.freeapps.util.bbc.BbcConstants.SINGLE_SCRIPT_LENGTH;

import com.coderdream.freeapps.util.other.CdFileUtils;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

/**
 * 功能：拆分句子 详情：根据分隔符拆分句子，并且保留分隔符。拆分后的句子限制字数是PRE_SIZE以内
 * <p>
 * 拆分规则： 分隔符(英文4个，中文4个) ,.?!，。？！ 最大字数限制(含标点)：PRE_SIZE 字数若超过限制，则在前一个分隔符切分。若前面没有分隔符，则在下一个分隔符切分
 * <p>
 * 句子案例： 您好，请问可以听见吗？ 不好意思，这个我不会哎！ 很抱歉打扰到您了，祝您生活愉快，再见。
 * 据报道，刘德华（华仔）日前为庆祝官方粉丝后援会华仔天地32周年，特地举办线上聚会，不仅献唱5首歌，还罕见谈到妻子和孩子的近况，相当宠粉，他也自嘲过去1年因生病和疫情影响巡演取消，发现自己眼角多了皱纹还长出白发，近日，他在后援会晒出近照，真实状态也曝光了。
 * @author CoderDream
 */
public class BreakUpToSentence {

    /**
     * 句子结束符.
     */
    private static final String SEPARATOR_REGEX = "[,.?!，。？！]";

    /**
     * 字数限制(包含标点).
     */
    private static final Integer SIZE = SINGLE_SCRIPT_LENGTH;


    /**
     * 字数限制(包含标点).
     */
    private static final Integer PRE_SIZE = 10;

    public static void main(String[] args) {
        //1. 需要拆分的句子
        String sentence = "And I’m Beth. As the world switches from fossil fuels to renewable sources of energy, solar panels are appearing in more and more places… and with good reason.";
        sentence = "and so the antithesis of that is CORFing which means ‘Cutting Off Reflected Failure’ and that is what people sometimes do when their team loses and they don't want to be associated with that loss";
        sentence = "What’s more, fandom has started to develop its own language. In this programme, we’ll be taking a trip into the world of fandoms, and, as usual, we’ll be learning some new and useful vocabulary too. But before that, I have a question for you, Neil. The original word ‘fan’ was first used about Americans in the early 20th century. But what were these Americans fans of? Was it:";

        sentence = "If you really love something, maybe a sport or a hobby, a music band or a TV show, you might call yourself a ‘fan’.";

//        sentence = "Hello World. "
//            + "Today in the U.S.A.,it is a nice day! "
//            + "Hurrah!"
//            + "The U.S. is a great country. "
//            + "Here it comes... "
//            + "Party time!";

        System.out.println("原句子：" + sentence);

//        BreakIterator iterator = BreakIterator.getSentenceInstance();
//        int start = iterator.first();
//        for (int end = iterator.next(); end != BreakIterator.DONE; start = end,end = iterator.next()) {
//            System.out.println(sentence.substring(start,end));
//        }

        // 分割成句子
//        breakToSentence(sentence);

        // 分割成字符串
        List<String> stringList = splitSentence(sentence);
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("#" + i + ": " + stringList.get(i));
        }
//
//        //2. 需要分割的文本
//        List<String> wordList = splitSentence(sentence);
//        wordList.stream().forEach(word -> {
//            System.out.println("word = " + word + "----------->length = " + word.length());
//        });

        //3. 输出 words
//        System.out.println("\n拆分后的句子：");
//        Arrays.stream(words).forEach(System.out::println);
//
//        //4. 限制拆分后的句子的字数在PRE_SIZE位以内
//        System.out.println("\n正在处理每个句子允许的字数是PRE_SIZE以内中.....");
//        List<String> wordList = limitNumber(words);
//
//        //5. 输出限制后的数据
//        System.out.println("\n最终处理完后的数据是： ");
//        wordList.stream().forEach(word -> {
//            System.out.println("word = " + word + "----------->length = " + word.length());
//        });
//        String fileName = "D:\\14_LearnEnglish\\6MinuteEnglish\\230914\\script_dialog.txt";
//        processScriptDialog(fileName);
    }

    public static List<String> breakToSentence(String sentence) {
        List<String> result = new ArrayList<>();
        BreakIterator boundary = BreakIterator.getSentenceInstance(Locale.ENGLISH);
        //设置要处理的文本
        boundary.setText(sentence);

        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {
//            System.out.println(sentence.substring(start, end));
            result.add(sentence.substring(start, end));
        }
        return result;
    }

    public static void processScriptDialog(String fileName) {

        //1. 需要拆分的句子
        String sentence = "And I’m Beth. As the world switches from fossil fuels to renewable sources of energy, solar panels are appearing in more and more places… and with good reason.";
        sentence = "Great! But first I have a question for you, Beth.  Harnessing the power of the sun is not new, in fact solar power dates back over 2,700 years. In 213 BC, mirrors were used to reflect sunlight back onto Roman ships attacking the city of Syracuse, causing them to catch fire. But which ancient Greek philosopher was responsible for this solar heat ray? Was it:";
        System.out.println("原句子：" + sentence);

        List<String> stringList = CdFileUtils.readFileContent(fileName);
//        for (String txt : stringList) {
//            breakSentence(txt);
//        }
//        text = stringList.stream().map(String::valueOf).collect(Collectors.joining(" "));

//        List<String> stringList = splitSentence(sentence);
//        for (int i = 0; i < stringList.size(); i++) {
//            System.out.println("#" + i + ": " + stringList.get(i));
//        }

    }

    /**
     * 通过分隔符拆分句子,并保留分隔符
     * <p>
     * 例如：很抱歉打扰到您了，祝您生活愉快，再见。 根据分隔符拆分： 很抱歉打扰到您了， 祝您生活愉快， 再见。
     *
     * @return
     */
    public static List<String> splitSentence(String sentence) {

        List<String> result = new ArrayList<>();

        int startIndex = 0;
        String sentenceTemp;

        if (sentence.length() > SIZE) {
            String[] arr = sentence.split(", ");
            for (int j = 0; j < arr.length; j++) {
                // 不处理，直接放入
                if (arr[j].length() > SIZE * 4) {
                    result.add(arr[j]);
                } else if (arr[j].length() > SIZE * 3 && arr[j].length() <= SIZE * 4) {
                    // 找第一个分割点，从SIZE-PRE_SIZE的位置开始找
                    int spaceIndex = 0;
                    // 寻找空格
                    for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                        char c = arr[j].charAt(k);
                        if (String.valueOf(c).equals(" ")) {
                            spaceIndex = k;
                            break;
                        }
                    }

                    // 没找到，直接退出
                    if (spaceIndex == 0) {
                        System.out.println("###ERROR###");
                        result.add(arr[j]);
                    } else {
                        // 将第一个短句放入列表
                        result.add(arr[j].substring(0, spaceIndex + 1));

                        String secondStr = arr[j].substring(spaceIndex + 1);
                        int secondSpaceIndex = 0;
                        // 寻找空格
                        for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                            char c = secondStr.charAt(k);
                            if (String.valueOf(c).equals(" ")) {
                                secondSpaceIndex = k;
                                break;
                            }
                        }
                        result.add(secondStr.substring(0, secondSpaceIndex + 1));

                        String thirdStr = secondStr.substring(secondSpaceIndex + 1);
                        int thirdSpaceIndex = 0;
                        // 寻找空格
                        for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                            char c = thirdStr.charAt(k);
                            if (String.valueOf(c).equals(" ")) {
                                thirdSpaceIndex = k;
                                break;
                            }
                        }
                        result.add(thirdStr.substring(0, thirdSpaceIndex + 1));
                        result.add(thirdStr.substring(thirdSpaceIndex + 1));
                    }
                } else if (arr[j].length() > SIZE * 2 && arr[j].length() <= SIZE * 3) {
                    if (j < arr.length - 1) {
                        arr[j] = arr[j] + ", ";
                    }

                    int spaceIndex = 0;
                    // 寻找空格
                    for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                        char c = arr[j].charAt(k);
                        if (String.valueOf(c).equals(" ")) {
                            spaceIndex = k;
                            break;
                        }
                    }

                    if (spaceIndex == 0) {
                        System.out.println("ERROR #1.a");
//                        result.add(arr[j]);

                        // 寻找空格
                        for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                            char c = arr[j].charAt(k);
                            if (String.valueOf(c).equals(" ")) {
                                spaceIndex = k;
                                break;
                            }
                        }

                        if (spaceIndex == 0) {
                            System.out.println("ERROR #1.b");
                            result.add(arr[j]);
                        } else {
                            result.add(arr[j].substring(0, spaceIndex + 1));

                            String secondStr = arr[j].substring(spaceIndex + 1);
                            int secondSpaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = secondStr.charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    secondSpaceIndex = k;
                                    break;
                                }
                            }
                            result.add(secondStr.substring(0, secondSpaceIndex + 1));
                            result.add(secondStr.substring(secondSpaceIndex + 1));
                        }

                    } else {
                        result.add(arr[j].substring(0, spaceIndex + 1));

                        String secondStr = arr[j].substring(spaceIndex + 1);
                        int secondSpaceIndex = 0;
                        // 寻找空格
                        for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                            char c = secondStr.charAt(k);
                            if (String.valueOf(c).equals(" ")) {
                                secondSpaceIndex = k;
                                break;
                            }
                        }
                        result.add(secondStr.substring(0, secondSpaceIndex + 1));
                        result.add(secondStr.substring(secondSpaceIndex + 1));
                    }
                } else if (arr[j].length() > SIZE && arr[j].length() <= SIZE * 2) {
                    if (j < arr.length - 1) {
                        arr[j] = arr[j] + ", ";
                    }

                    int spaceIndex = 0;
                    // 寻找空格
                    for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                        char c = arr[j].charAt(k);
                        if (String.valueOf(c).equals(" ")) {
                            spaceIndex = k;
                            break;
                        }
                    }

                    if (spaceIndex == 0) {
//                        System.out.println("ERROR #2 : " + arr[j].length() + ":" + arr[j]);
//                        result.add(arr[j]);
                        // 寻找空格
                        for (int k = SIZE - PRE_SIZE * 2; k < SIZE; k++) {
                            char c = arr[j].charAt(k);
                            if (String.valueOf(c).equals(" ")) {
                                spaceIndex = k;
                                break;
                            }
                        }

                        if (spaceIndex == 0) {
                            System.out.println("ERROR #2 : " + arr[j].length() + ":" + arr[j]);
                            result.add(arr[j]);


                        } else {
                            result.add(arr[j].substring(0, spaceIndex + 1));
                            result.add(arr[j].substring(spaceIndex + 1));
                        }

                    } else {
                        result.add(arr[j].substring(0, spaceIndex + 1));
                        result.add(arr[j].substring(spaceIndex + 1));
                    }
                } else {
                    if (j == arr.length - 1) {
                        result.add(arr[j]);
                    } else {
                        result.add(arr[j] + ",");
                    }
                }
            }
        } else {
            result.add(sentence);
        }

        return result;
    }

    public static List<String> splitSentenceBackup(String sentence) {
        Set<String> regexSet = new TreeSet<>();
        regexSet.add("!");
        regexSet.add("?");
        regexSet.add(".");
        regexSet.add("–");
        regexSet.add("…");
        regexSet.add(":");

        List<String> result = new ArrayList<>();

        int startIndex = 0;
        String sentenceTemp;
        for (int i = 0; i < sentence.length(); i++) {
            String substring = sentence.substring(i, i + 1);
            // 找到了，开始分割并记录
            if (true) { //regexSet.contains(substring)) {
                sentenceTemp = sentence.substring(startIndex, i + 1);
                sentenceTemp = sentenceTemp;
                System.out.println(sentenceTemp);
                if (sentenceTemp.length() > SIZE) {
                    String[] arr = sentenceTemp.split(", ");
                    for (int j = 0; j < arr.length; j++) {
                        // 不处理，直接放入
                        if (arr[j].length() > SIZE * 4) {
                            result.add(arr[j]);
                        } else if (arr[j].length() > SIZE * 3 && arr[j].length() <= SIZE * 4) {
                            // 找第一个分割点，从SIZE-PRE_SIZE的位置开始找
                            int spaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = arr[j].charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    spaceIndex = k;
                                    break;
                                }
                            }

                            // 没找到，直接退出
                            if (spaceIndex == 0) {
                                System.out.println("ERROR #3");
                                result.add(arr[j]);
                            }
                            // 将第一个短句放入列表
                            result.add(arr[j].substring(0, spaceIndex + 1));

                            String secondStr = arr[j].substring(spaceIndex + 1);
                            int secondSpaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = secondStr.charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    secondSpaceIndex = k;
                                    break;
                                }
                            }
                            result.add(secondStr.substring(0, secondSpaceIndex + 1));

                            String thirdStr = secondStr.substring(secondSpaceIndex + 1);
                            int thirdSpaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = thirdStr.charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    thirdSpaceIndex = k;
                                    break;
                                }
                            }
                            result.add(thirdStr.substring(0, thirdSpaceIndex + 1));
                            result.add(thirdStr.substring(thirdSpaceIndex + 1));
                        } else if (arr[j].length() > SIZE * 2 && arr[j].length() <= SIZE * 3) {
                            if (j < arr.length - 1) {
                                arr[j] = arr[j] + ", ";
                            }

                            int spaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = arr[j].charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    spaceIndex = k;
                                    break;
                                }
                            }

                            if (spaceIndex == 0) {
                                System.out.println("ERROR #4");
                                result.add(arr[j]);
                            }

                            result.add(arr[j].substring(0, spaceIndex + 1));

                            String secondStr = arr[j].substring(spaceIndex + 1);
                            int secondSpaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = secondStr.charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    secondSpaceIndex = k;
                                    break;
                                }
                            }
                            result.add(secondStr.substring(0, secondSpaceIndex + 1));
                            result.add(secondStr.substring(secondSpaceIndex + 1));
                        } else if (arr[j].length() > SIZE && arr[j].length() <= SIZE * 2) {
                            if (j < arr.length - 1) {
                                arr[j] = arr[j] + ", ";
                            }

                            int spaceIndex = 0;
                            // 寻找空格
                            for (int k = SIZE - PRE_SIZE; k < SIZE; k++) {
                                char c = arr[j].charAt(k);
                                if (String.valueOf(c).equals(" ")) {
                                    spaceIndex = k;
                                    break;
                                }
                            }

                            if (spaceIndex == 0) {
                                System.out.println("ERROR #5");
                                result.add(arr[j]);
                            }

                            result.add(arr[j].substring(0, spaceIndex + 1));
                            result.add(arr[j].substring(spaceIndex + 1));
                        } else {
                            if (j == arr.length - 1) {
                                result.add(arr[j]);
                            } else {
                                result.add(arr[j] + ",");
                            }
                        }
                    }
                } else {
                    result.add(sentenceTemp);
                }

                startIndex = i + 1;
            }
        }

        // 合并，如果连续多行加起来没有超过SIZE，则相加
//        List<String> newResult = new ArrayList<>();
//        int size = result.size();
//        if(size > 2) {
//            int firstLength = result.get(0).length();
//            for (int i = 1; i < size; i++) {
//             //   int
//            }
//
//
//            result = newResult;
//        }


        return result;
    }

    /**
     * 限制拆分后的句子的字数包括分隔符在PRE_SIZE位以内
     * <p>
     * 例如： 句子：很抱歉打扰到您了，祝您生活愉快，再见。 根据分隔符拆分后： 很抱歉打扰到您了， 祝您生活愉快， 再见。 限制字数后： 很抱歉打扰到您了， 祝您生活愉快，再见。
     *
     * @param words
     * @return
     */
    public static List limitNumber(String[] words) {
        //1. 存储限制字数的数据
        List<String> wordList = new ArrayList<>();

        //2. 限制字数在PRE_SIZE以内
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

