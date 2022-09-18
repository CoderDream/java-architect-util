package thread;

// 引入ExecutorService类
import java.util.concurrent.ExecutorService;
// 引入Executors类
import java.util.concurrent.Executors;
// 引入Callable接口
import java.util.concurrent.Callable;
// 引入Future类
import java.util.concurrent.Future;
// 引入List
import java.util.List;
// 引入ArrayList
import java.util.ArrayList;

/**
 * 线程池：缓存线程池，实现多线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class CachedPoolCallableTest {

    public static void main(String[] args) throws Exception{
        // 新建线程池：缓存线程池，实现多线程
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        // 多线程：线程列表，存储线程
        List<Future<String>> futureLis = new ArrayList<Future<String>>();
        // 新建多线程：5个线程
        for(int i = 0; i < 5; i++) {
            // 线程执行
            Future<String> futureTemp = cachedPool.submit(new taskExe(3));
            futureLis.add(futureTemp);
        }
        // 线程任务执行状态
        for(Future<String> futureLi : futureLis) {
            // 逻辑执行结束，获取线程执行返回结果
            while(!futureLi.isDone()) {
                System.out.println("Future: " + futureLi.get());
            }
        }
        // 关闭线程池
        cachedPool.shutdown();

    }

    /**
     * 实现Callable接口,
     * 结合FutureTask新建线程，
     * 重写call()方法，执行逻辑
     */
    public static class taskExe implements Callable<String> { 

        private int code;

        // 构造方法
        public taskExe(int code){
            this.code = code;
        }
        
        // 重写call()方法
        @Override 
        public String call() throws Exception {
            for(int i = code; i > 0; i--){
                System.out.println("Thread: " + Thread.currentThread() + "i: " + i);
            }
            // 返回：字符串
            return "finished";
        }
    }

    
}