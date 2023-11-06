package cn.xupengzhuang.chapter06;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 再次优化，仅用质数来做被测试数的除数，并且这个质数需要小于被测试数的平方根
 */
@Slf4j
public class IsPrimeTests {

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(new PrimeNumbersCollector());
    }

    @Test
    public void testIsPrime(){
        Map<Boolean, List<Integer>> map = partitionPrimesWithCustomCollector(20);
        log.info("{}",map);
    }

    /**
     * 测试 partitionPrimesWithCustomCollector 的性能
     */
    @Test
    public void testHarness(){
        long fastest = Integer.MAX_VALUE;
        for (int i=0;i<10;i++){
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1000000);
            //取运行时间的毫秒值
            long duration = (System.nanoTime() -  start) / 1000000;
            if (duration < fastest){
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
        //Fastest execution done in 958 msecs

    }





}
