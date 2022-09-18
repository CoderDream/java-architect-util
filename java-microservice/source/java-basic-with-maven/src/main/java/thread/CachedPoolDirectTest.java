package thread;

// 引入ExecutorService类
import java.util.concurrent.ExecutorService;
// 引入Executors类
import java.util.concurrent.Executors;

/**
 * 线程池：缓存线程池，实现单线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class CachedPoolDirectTest {

    // 主函数
    public static void main(String[] args) throws Exception{
        // 新建线程池：缓存线程池，单线程形式
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        for(int i = 0; i < 3; i++) {
            final int index = i;
            try{
                /**
                 * 线程休眠：200毫秒
                 * 调用本地方法
                 */
                Thread.sleep(index * 100);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            
            /**
             * 提交线程执行任务，
             * 实现Runnable接口，
             * 重写run()方法，
             * 在run()方法中实现逻辑
             */
            cachedPool.submit(new Runnable() {
                public void run() {
                    for(int j = 0; j < 2; j++){
                        System.out.println("Thread submit: " + Thread.currentThread() + ",j: " + j);
                    }
                }
            });
        }
        // 关闭线程池
        cachedPool.shutdown();

    }

    
}