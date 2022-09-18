package thread;

// 引入Runnable接口
import java.lang.Runnable;
// 引入Thread类
import java.lang.Thread;

/**
 * 实现Runnable接口：新建线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class RunnableTest implements Runnable{
    
    // 重写run()方法
    @Override
    public void run(){
        for(int i=0; i<5; i++){
            try{
                /**
                 * 线程休眠：200毫秒
                 * 调用本地方法
                 */
                Thread.sleep(200);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // 输出当前线程
            System.out.println("Thread:" + Thread.currentThread() + ",i:" + i);
        }
    }

    // 主函数
    public static void main(String[] args){
        /**
         * 类实例化
         * 新建Runnable接口实例，
         * 作为初始化Thread的参数
         */
        RunnableTest runnableTest1 = new RunnableTest();
        /**
         * 类实例化
         * 新建线程thread1和thread2
         */
        Thread thread1 = new Thread(runnableTest1, "线程1");
        Thread thread2 = new Thread(runnableTest1);
        /**
         * 线程执行
         * 调用同步方法（synchronized）start(),
         * 该同步方法调用本地方法start0()
         */
        thread1.start();
        thread2.start();
    }
    
}