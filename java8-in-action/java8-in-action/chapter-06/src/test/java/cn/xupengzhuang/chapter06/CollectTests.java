package cn.xupengzhuang.chapter06;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static cn.xupengzhuang.chapter06.Dish.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Slf4j
public class CollectTests {

    /**
     * 统计总条数
     */
    @Test
    public void testCounting(){
        Long howManyDishes = menu.stream().collect(counting());
        log.info("how many dishes={}",howManyDishes);

        long count = menu.stream().count();
        log.info("count={}",count);
    }

    /**
     * 计算流中的最大值
     */
    @Test
    public void testMaxBy(){
        Comparator<Dish> comparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(comparator));
        Dish dish = mostCalorieDish.get();
        log.info("most calorie dish is '{}',calorie={} ",dish.getName(),dish.getCalories());
    }

    /**
     * 计算流中的最小值
     */
    @Test
    public void testMinBy(){
        Comparator<Dish> dishComparator = comparingInt(Dish::getCalories);
        Optional<Dish> dishOptional = menu.stream().collect(minBy(dishComparator));
        Dish dish = dishOptional.get();
        log.info("min calorie dish is '{}',calorie={}", dish.getName(),dish.getCalories());
    }

    /**
     * 求和
     */
    @Test
    public void testSumming(){
        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        log.info("total calories={}",totalCalories);
    }

    /**
     * 求平均数
     */
    @Test
    public void testAveraging(){
        Double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        log.info("average calories={}",averageCalories);
    }

    /**
     * 获取个数、总和、最大值、最小值、平均值
     */
    @Test
    public void testSummarizing(){
        //summarizingInt 适用于收集的属性是int类型，相应的还有summarizingLong、summarizingDouble
        IntSummaryStatistics summary = menu.stream().collect(summarizingInt(Dish::getCalories));
        log.info("summary sum={}",summary.getSum());
        log.info("summary count={}",summary.getCount());
        log.info("summary max={}",summary.getMax());
        log.info("summary min={}",summary.getMin());
        log.info("summary average={}",summary.getAverage());
    }

    /**
     * 字符串拼接
     */
    @Test
    public void testJoining(){
        String str = menu.stream().map(Dish::getName).collect(joining(","));
        log.info("str={}",str);
    }

    @Test
    public void testReducingSum(){
        Integer integer = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        log.info("calories sum={}",integer);
    }

    @Test
    public void testReducingMax(){
        Optional<Dish> optional = menu.stream().collect(reducing((a, b) -> a.getCalories() > b.getCalories() ? a : b));
        log.info("max calories={},name={}",optional.get().getCalories(),optional.get().getName());
    }

    @Test
    public void testReducingMin(){
        Optional<Dish> optional = menu.stream().collect(reducing((a, b) -> a.getCalories() < b.getCalories() ? a : b));
        log.info("min calories={},name={}",optional.get().getCalories(),optional.get().getName());
    }

    @Test
    public void testReduce(){
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l; },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1; });

        log.info("numbers={}",numbers);
    }

    /**
     * 归约操作的原理：利用累计函数，把一个初始化为起始值的累加器，和把转换函数应用到流中每个元素上得到的结果不断迭代合并起来
     * reducing的第一个参数：起始值
     *          第二个参数：转换函数
     *          第三个参数：累计函数
     */
    @Test
    public void testIntegerSum(){
        Integer sum = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        log.info("calories sum=" + sum);
    }

    @Test
    public void testMapReduce(){
        Integer caloriesSum = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        log.info("caloriesSum={}",caloriesSum);
    }

    @Test
    public void testMapToInt(){
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        log.info("sum={}",sum);
    }




}
