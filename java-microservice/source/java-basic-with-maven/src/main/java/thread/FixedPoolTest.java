package thread;

// 引入ExecutorService类
import java.util.concurrent.ExecutorService;
// 引入Executors类
import java.util.concurrent.Executors;

/**
 * 线程池：新建固定数量的线程
 * @author xindaqi
 * @since 2020-10-10
 */
public class FixedPoolTest {

    // 主函数
    public static void main(String[] args) {
        // 新建线程池：指定线程数量3
        ExecutorService exs = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 3; i++) {
            /**
             * 提交线程执行任务，
             * 实现Runnable接口，
             * 重写run()方法，
             * 在run()方法中实现逻辑
             */
            exs.submit(
                new Runnable(){
                    @Override
                    public void run() {
                        for(int j = 0; j < 2; j++) {
                            System.out.println("Thread: " + Thread.currentThread() + ",j:" + j);
                        }
                    }
                }
            );
        }
        // 关闭线程池
        exs.shutdown();
    }
    
}