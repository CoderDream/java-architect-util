package thread;

// 引入Executors类
import java.util.concurrent.Executors;
// 引入ScheduledExecutorService接口
import java.util.concurrent.ScheduledExecutorService;
// 引入TimeUnit类
import java.util.concurrent.TimeUnit;
// 引入LocalDateTime类
import java.time.LocalDateTime;

/**
 * 线程池：定时线程池
 * @author xindaqi
 * @since 2020-10-10
 */
public class ScheduledPoolCircleTest {

    // 主函数
    public static void main(String[] args) {
        // 新建线程池：定时线程池
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        // 当前时间
        System.out.println("Time start:" + LocalDateTime.now());

        /**
         * 定时任务：实现Runnable方接口，
         * 重写run()方法，
         * 延迟2秒，并每隔3秒执行
         * */ 
        ses.scheduleAtFixedRate(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("Delay 2 seconds and executive in every 3 seconds.");
                    System.out.println("Time end:" + LocalDateTime.now());
                }
            }, 2, 3, TimeUnit.SECONDS);
        // ses.shutdown();

    }
    
}