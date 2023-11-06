package cn.xupengzhuang.chapter11;

import com.alibaba.fastjson.parser.JSONToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class CompletableFutureTests {

    @Test
    public void test1() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> doubleFuture = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomethingComputation();
            }
        });
        System.out.println("do somthing else");
        Double result = null;
        try {
            //获取异步操作的结果，如果最终被阻塞，无法获取结果，那么在最多等待5秒钟后退出。
            result = doubleFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public Double doSomethingComputation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1.0;
    }
}
