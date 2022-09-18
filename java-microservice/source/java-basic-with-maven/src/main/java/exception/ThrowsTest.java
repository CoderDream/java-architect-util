package exception;

import java.util.logging.Logger;
import static common.StringConstant.*;

/**
* @description 异常抛出及捕获测试
* @author xindaqi
* @since 2020-12-30 15:00:34
*/
public class ThrowsTest {

    private static Logger logger = Logger.getLogger("ThrowsTest");

    /**
     * return在try
     *
     * @param a 入参
     * @return 处理结果
     */
    private static StringBuilder returnInTry(int a) {
        StringBuilder str = new StringBuilder(10);
        try {
            int b = 1/a;
            
            str.append(TRY);
            logger.info("我是日志信息");
            System.out.println(FROM + str);
            return str.append(TRY);
        } catch(Exception e) {
            str.append(CATCH);
            System.out.println(FROM + str);
        } finally{
            str.append(FINALLY);
            System.out.println(FROM + str);
        }
        return str.append(END);
    }

    /**
     * return在try和catch
     *
     * @param a 入参
     * @return 处理结果
     */
    private static StringBuilder returnInTryAndCatch(int a) {
        StringBuilder str = new StringBuilder(10);
        try {
            int b = 1/a;
            str.append(TRY);
            System.out.println(FROM + str);
            return str.append(TRY);
        } catch(Exception e) {
            str.append(CATCH);
            System.out.println(FROM + str);
            return str.append(CATCH);
        } finally{
            str.append(FINALLY);
            System.out.println(FROM + str);
        }
    }

    /**
     * return在try、catch和finally
     *
     * @param a 入参
     * @return 处理结果
     */
    private static StringBuilder returnInTryAndCatchAndFinally(int a) {
        StringBuilder str = new StringBuilder(10);
        try {
            int b = 1/a;
            str.append(TRY);
            System.out.println(FROM + str);
            return str.append(TRY);
        } catch(Exception e) {
            str.append(CATCH);
            System.out.println(FROM + str);
            return str;
        } finally{
            str.append(FINALLY);
            System.out.println(FROM + str);
            return str.append(FINALLY);
        }
    }

    private static void printLine() {
        System.out.println("============");
    }

    public static void main(String[] args) {
        StringBuilder str = returnInTry(1);
        System.out.println(RESULT + str);
        printLine();
        StringBuilder str1 = returnInTry(0);
        System.out.println(RESULT + str1);
        printLine();
        StringBuilder str2 = returnInTryAndCatch(1);
        System.out.println(RESULT + str2);
        printLine();
        StringBuilder str3 = returnInTryAndCatch(0);
        System.out.println(RESULT + str3);
        printLine();
        StringBuilder str4 = returnInTryAndCatchAndFinally(1);
        System.out.println(RESULT + str4);
        printLine();
        StringBuilder str5 = returnInTryAndCatchAndFinally(0);
        System.out.println(RESULT + str5);
        printLine();
    }
    
}