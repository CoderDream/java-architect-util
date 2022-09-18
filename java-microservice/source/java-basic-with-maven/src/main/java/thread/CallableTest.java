package thread;

// 引入Callable接口
import java.util.concurrent.Callable;
// 引入ExecutionException类
import java.util.concurrent.ExecutionException;
// 引入FutureTask类
import java.util.concurrent.FutureTask;

/**
 * 实现Runnable接口：创建线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class CallableTest implements Callable<String>{
    // 循环变量
    private int count = 10;

    // 重写call()方法
    @Override
    public String call() throws Exception {
        for(int i = 0; i < 3; i++) {
            System.out.println("Thread:" + Thread.currentThread() + ",i:" + i);
        }
        // 返回数据：字符串
        return "Empty";
    }

    // 主函数
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        // 类实例化：返回字符串数据
        Callable<String> callable = new CallableTest();
        // 类实例化：以Callable接口实现类实例为参数
        FutureTask<String> futureTask = new FutureTask<>(callable);
        /**
         * 创建线程
         * 以FutureTask实例为Thread参数入参
         */
        Thread thread1 = new Thread(futureTask);
        
        /**
         * 线程执行
         * 调用同步方法（synchronized）start(),
         * 该同步方法调用本地方法start0()
         */
        thread1.start();
        Thread thread2 = new Thread(futureTask);
        System.out.println("Future task:" + futureTask.get());
        thread2.start();
        // 线程返回
        System.out.println("Future task:" + futureTask.get());
    }


    
}