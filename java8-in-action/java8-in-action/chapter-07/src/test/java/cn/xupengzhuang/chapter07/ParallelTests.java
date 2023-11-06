package cn.xupengzhuang.chapter07;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
public class ParallelTests {

    @Test
    public void testCore(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
    }

    public static long sequentialSum(long n){
        return Stream.iterate(1L,i -> i + 1)
                .limit(n)
                .reduce(0L,(i,j) -> i + j);
    }

    @Test
    public void testSequentialSum(){
        long sum = sequentialSum(100);
        log.info("sum={}",sum);
    }

    public static long iterativeSum(long n){
        long result = 0l;
        for (long i=1l;i<=n;i++){
            result = result + i;
        }
        return result;
    }

    @Test
    public void testIterativeSum(){
        long sum = iterativeSum(100);
        log.info("sum={}",sum);
    }

    /**
     * 将顺序流转换为并行流
     */
    public static long parallelSum(long n){
        return Stream.iterate(1l,i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0l,(i,j) -> i + j);
    }

    @Test
    public void testParallelSum(){
        long sum = parallelSum(100);
        log.info("sum={}",sum);
    }

    public long measureSumPerf(Function<Long,Long> adder,long n){
        long fastest = Long.MAX_VALUE;
        for (int i=0;i<10;i++){
            long start = System.nanoTime();
            Long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
            System.out.println("result=" + sum);
            if (duration < fastest){
                fastest = duration;
            }
        }
        return fastest;
    }

    @Test
    public void testMeasureSumPerfOne(){
        long time = measureSumPerf(ParallelTests::sequentialSum, 10000000);
        log.info("sequentialSum time={}",time);//174
    }

    @Test
    public void testMeasureSumPerfTwo(){
        long time = measureSumPerf(ParallelTests::iterativeSum, 10000000);
        log.info("iterativeSum time={}",time);//13
    }

    @Test
    public void testMeasureSumPerfThree(){
        long time = measureSumPerf(ParallelTests::parallelSum, 10000000);
        log.info("parallelSum time={}",time);//95
    }

    public static long rangedSum(long n){
        return LongStream.rangeClosed(1,n).reduce(0l,Long::sum);
    }
    @Test
    public void testMeasureSumPerfFour(){
        long time = measureSumPerf(ParallelTests::rangedSum, 10000000);
        log.info("rangedSum time={}",time);//12
    }

    public static long rangedSumParallel(long n){
        return LongStream.rangeClosed(1,n).parallel().reduce(0l,Long::sum);
    }
    @Test
    public void testMeasureSumPerfFive(){
        long time = measureSumPerf(ParallelTests::rangedSumParallel, 10000000);
        log.info("rangedSumParallel time={}",time);//3
    }

    /**
     * 顺序执行
     * @param n
     * @return
     */
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }
    /**
     * 并行执行时，结果错误
     * @param n
     * @return
     */
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
    @Test
    public void testSideEffectParallelSum(){
        System.out.println("SideEffect parallel sum done in: " +
                measureSumPerf(ParallelTests::sideEffectSum, 10_000_000L) +" msecs" );
    }

}

class Accumulator {
    public long total = 0;
    public void add(long value) { total += value; }
}
