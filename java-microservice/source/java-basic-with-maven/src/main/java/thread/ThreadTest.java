package thread;

// 引入Thread类
import java.lang.Thread;

/**
 * 继承Thread类：新建线程
 * @author xindaqi
 * @since 2020-10-10
 */

public class ThreadTest extends Thread{
    // 构造方法
    public ThreadTest(){}

    // 重写run()方法
    public void run(){
        for(int i = 0; i < 5; i++){
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
            System.out.println("Thread:"  + Thread.currentThread()+ ":" + i );
        }
    }

    // 主函数
    public static void main(String[] args){
        /**
         * 类实例化
         * 新建线程thread1和thread2
         */
        ThreadTest thread1 = new ThreadTest();
        ThreadTest thread2 = new ThreadTest();
        /**
         * 线程执行
         * 调用同步方法（synchronized）start(),
         * 该同步方法调用本地方法start0()
         */
        thread1.start();
        thread2.start();

    }
    
}