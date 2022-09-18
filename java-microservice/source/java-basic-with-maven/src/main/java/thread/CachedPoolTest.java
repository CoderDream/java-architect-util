package thread;

// 引入ExecutorService类
import java.util.concurrent.ExecutorService;
// 引入Executors类
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.List;
import java.util.ArrayList;

/**
 * 线程池：缓存线程池，实现多线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class CachedPoolTest {

    public static void main(String[] args) throws Exception{
        // 新建线程池：缓存线程池，实现多线程
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        // Future<String> future = cachedPool.submit(new taskExe(5));
        // System.out.println("Futrue: " + future.get()); 
        // cachedPool.shutdown();

        // List<Future<String>> futureLis = new ArrayList<Future<String>>();
        // for(int i = 0; i < 10; i++) {
        //     Future<String> futureTemp = cachedPool.submit(new taskExe(3));
        //     futureLis.add(futureTemp);
        // }
        // for(Future<String> futureLi : futureLis) {
        //     while(!futureLi.isDone()) {
        //         System.out.println("Future: " + futureLi.get());
        //     }
        // }
        
        // cachedPool.shutdown();
        
        for(int i = 0; i < 5; i++) {
            // final int index = i;
            int index = i;
            try{
                Thread.sleep(index * 100);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }

        //     cachedPool.execute(new Runnable() {
        //         public void run() {
        //             System.out.println("Thread execute:" + Thread.currentThread() +"index: " + index);
        //         }
        //     });
            


            cachedPool.submit(new Runnable() {
                public void run() {
                    for(int j = 0; j < 2; j++){
                        System.out.println("Thread submit: " + Thread.currentThread() + "index: " + j);
                    }
                }
            });


        }

    }

    public static class taskExe implements Callable<String> { 

        private int code;

        public taskExe(int code){
            this.code = code;
        }
        
        @Override 
        public String call() throws Exception {
            for(int i = code; i > 0; i--){
                System.out.println("Thread: " + Thread.currentThread() + "i: " + i);
            }
            
            return "finished";
        }
    }

    
}