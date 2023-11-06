package cn.xupengzhuang.chapter05;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static cn.xupengzhuang.chapter05.Transaction.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Slf4j
public class TransactionTests {

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     */
    @Test
    public void test1(){
        List<Transaction> list = transactions.stream()
                .filter(d -> d.getYear() == 2011)
                .sorted((d1, d2) -> d1.getValue() > d2.getValue() ? 1 : -1)
                .collect(toList());
        log.info("{}", JSON.toJSONString(list));
    }

    /**
     * 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test2(){
        List<String> list = transactions.stream()
                .map(d -> d.getTrader().getCity()).distinct()
                .collect(toList());
        log.info("{}",list);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    @Test
    public void test3(){
        List<Trader> list = transactions.stream()
                .map(d -> d.getTrader())
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName)).collect(toList());
        log.info("{}",list);

    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序。
     */
    @Test
    public void test4(){
        String s = transactions.stream()
                .map(t -> t.getTrader())
                .map(t -> t.getName())
                .sorted()
                .reduce("", (a, b) -> a + b);
        log.info("{}",s);
    }

    /**
     * 有没有交易员是在米兰工作的？
     */
    @Test
    public void test5(){
        boolean b = transactions.stream().map(t -> t.getTrader())
                .anyMatch(t -> "Milan".equals(t.getCity()));
        log.info("{}",b);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额。
     */
    @Test
    public void test6(){
        List<Integer> list = transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(t -> t.getValue())
                .collect(toList());
        log.info("{}",list);
    }

    /**
     * 所有交易中，最高的交易额是多少？
     */
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream().map(t -> t.getValue())
                .max((a, b) -> a > b ? 1 : -1);
        max.ifPresent(a -> System.out.println(a));

        Optional<Transaction> max1 = transactions.stream().max(comparing(Transaction::getValue));
        max1.ifPresent(a -> System.out.println(a.getValue()));
    }

    /**
     * 找到交易额最小的交易。
     */
    @Test
    public void test8(){
        Optional<Integer> optional = transactions.stream().map(t -> t.getValue())
                .reduce((a, b) -> a < b ? a : b);
        optional.ifPresent(a -> System.out.println(a));

        Optional<Transaction> min = transactions.stream().min(comparing(Transaction::getValue));
        min.ifPresent(a -> System.out.println(a.getValue()));
    }
}
