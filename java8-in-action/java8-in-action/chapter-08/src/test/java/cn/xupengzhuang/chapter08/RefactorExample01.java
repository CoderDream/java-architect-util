package cn.xupengzhuang.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 代码重构案例一：从匿名类到Lambda表达式的转换
 *
 */
@Slf4j
public class RefactorExample01 {

    @Test
    public void testAnonymous(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Anonymous");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    public void testLambda1(){
        Runnable runnable = () -> System.out.println("Hello Lambda");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 在匿名类中，this代表的是类自身，但是在Lambda表达式中，它代表的是包含类。
     * 匿名类可以屏蔽包含类的变量，而Lambda表达式则不能。
     */
    @Test
    public void testLambda2(){
        int a = 1;
        /*Runnable r1 = () -> {
            //编译错误
            int a = 2;
            System.out.println(a);
        };*/

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                //一切正常
                int a = 3;
            }
        };
    }

    /**
     * 匿名类的类型是在初始化的时候确定的，而Lambda表达式的类型则取决于它的上下文
     */
    @Test
    public void testLambda3(){
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Anonymous execute");
            }
        });

        //doSomething(Runnable runnable) 与 doSomething(Task task) 都可以匹配下面的lambda表达式
        //doSomething(() -> System.out.println("Danger danger"));

        //可以对Task尝试使用显式的类型转换来解决这种模棱两可的情况
        doSomething((Task) () -> System.out.println("Danger danger"));
    }

    public static void doSomething(Runnable runnable){
        runnable.run();
    }

    public static void doSomething(Task task){
        task.execute();
    }



}
