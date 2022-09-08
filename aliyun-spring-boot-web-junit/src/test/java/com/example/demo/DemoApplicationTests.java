package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("OOOOOKKKKKKK");
    }

    @Test
    void testRegexMatches_01() {
        String REGEX = "\\bcat\\b";
        String INPUT = "cat cat cat cattie cat";

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }

    @Test
    void testRegexMatches_02() {
        final String REGEX = "foo";
        final String INPUT = "fooooooooooooooooo";
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);

        System.out.println("Current REGEX is: " + REGEX);
        System.out.println("Current INPUT is: " + INPUT);

        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
    }

    @Test
    void testRegexMatches_03() {
        final String REGEX = "foo";
        final String INPUT = "fooooooooooooooooo";
        final String INPUT2 = "ooooofoooooooooooo";
        Pattern pattern;
        Matcher matcher;
        Matcher matcher2;

        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        matcher2 = pattern.matcher(INPUT2);

        System.out.println("Current REGEX is: " + REGEX);
        System.out.println("Current INPUT is: " + INPUT);
        System.out.println("Current INPUT2 is: " + INPUT2);


        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
        System.out.println("lookingAt(): " + matcher2.lookingAt());
    }

    @Test
    void testRegexMatches_04() {
        // 完全匹配"^[1-9][0-9]{4,}$"
        // 部分匹配"[1-9][0-9]{4,}"
        if (matcherRegularExpression("^[1-9][0-9]{4,}", "910618858a")) {
            System.out.println("输入正确");
        } else {
            System.out.println("输入错误");
        }
    }

    // 匹配正则表达式方法
    public static boolean matcherRegularExpression(String regEx, String str) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean found = false;
        while (matcher.find()) {
            //System.out.println("发现 \"" + matcher.group() + "\" 开始于 "
            //+ matcher.start() + " 结束于 " + matcher.end());
            found = true;
        }
        return found;
    }

    public List<String> getString(String s) {

        List<String> strs = new ArrayList<String>();
        Pattern p = Pattern.compile("GraphType\\s*=\\s*\".+\"\\s*");
        Matcher m = p.matcher(s);
        while (m.find()) {
            strs.add(m.group());

        }
        return strs;
    }


}
