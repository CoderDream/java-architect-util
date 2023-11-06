package cn.xupengzhuang.chapter06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 自定义质数收集器
 *
 * 泛型含义：
 *  Integer 流中元素类型
 *  Map<Boolean, List<Integer>> 累加器类型
 *  Map<Boolean, List<Integer>> collect操作的结果类型
 */
public class PrimeNumbersCollector implements Collector<Integer,Map<Boolean, List<Integer>>, Map<Boolean,List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean,List<Integer>>(){{
                put(true,new ArrayList<>());
                put(false,new ArrayList<>());
            }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc,Integer candidate) -> {
            //根据isPrime的结果，获取质数或非质数列表，将被测试数添加到相应的列表中。
            acc.get(isPrime(acc.get(true),candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean,List<Integer>> map1,Map<Boolean,List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i=0;
        for(A item : list){
            if (!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    /**
     * 判断一个数字是不是质数
     * @param primes 被测试数之前的质数列表
     * @param candidate 被测试数
     * @return
     */
    private static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes,i -> i <= candidateRoot).stream().noneMatch(j -> candidate % j == 0);
    }
}
