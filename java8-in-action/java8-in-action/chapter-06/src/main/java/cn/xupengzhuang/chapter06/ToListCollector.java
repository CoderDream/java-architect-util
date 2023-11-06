package cn.xupengzhuang.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {
    /**
     * 用于创建一个空的累加器实例，供数据收集过程使用
     * @return 结果为空的Supplier
     */
    @Override
    public Supplier<List<T>> supplier() {
        //return () -> new ArrayList<T>();
        return ArrayList::new;
    }

    /**
     * 用于将元素添加到结果容器
     * @return 执行规约操作的函数
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //return (list,item) -> list.add(item);
        return List::add;
    }

    /**
     * 返回供规约操作使用的函数，它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并。
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 对结果容器应用最终转换
     * @return 返回在累积过程中的最后要调用的一个函数，以便将累加器对象转换为整个集合操作的最终结果
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义了收集器的行为，主要是流是否可以并行规约，以及可以使用那些优化的提示
     *
     * UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
     * CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
     * IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检查地转换为结果R是安全的。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
