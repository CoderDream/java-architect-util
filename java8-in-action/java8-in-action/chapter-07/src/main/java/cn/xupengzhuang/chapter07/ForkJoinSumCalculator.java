package cn.xupengzhuang.chapter07;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    //要求和的数组
    private final long[] numbers;
    //任务的开始
    private final int start;
    //任务的结束
    private final int end;

    //不再将任务划分为子任务的数组的大小
    private static final long THRESHOLD = 10_000;

    /**
     * 公共构造函数，用于创建主任务
     * @param numbers
     */
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    /**
     * 私有构造函数用于以递归方式为主任务创建子任务
     * @param numbers
     * @param start
     * @param end
     */
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD){
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start + length / 2);
        //利用另一个ForkJoinPool线程异步执行新创建的子任务。
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果没有完成就等待
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially(){
        long sum=0;
        for (int i=start;i<end;i++){
            sum += numbers[i];
        }
        return sum;
    }
}
