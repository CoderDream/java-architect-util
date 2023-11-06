package cn.xupengzhuang.chapter07;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;

@Slf4j
public class ForkJoinTests {

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long measureSumPerf(Function<Long,Long> adder,long n){
        long fastest = Long.MAX_VALUE;
        for (int i=0;i<10;i++){
           long start = System.nanoTime();
            Long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 100_0000;
            System.out.println("result=" + sum);
            if (duration < fastest){
                fastest = duration;
            }
        }
        return fastest;
    }

    @Test
    public void testForkJoin(){
        long sum = forkJoinSum(100);
        log.info("sum={}",sum);
    }

    @Test
    public void testForkJoinMeasureSumPerf(){
        long time = measureSumPerf(ForkJoinTests::forkJoinSum, 1000_0000);
        log.info("time={}",time);//25
    }

}
