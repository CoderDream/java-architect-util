package log;

import java.util.logging.Logger;

/**
 * Log测试
 * @author xindaqi
 * @since 2021-01-23
 */
public class LogTest {

    private static final Logger logger = Logger.getLogger("LogTest");

    public static void main(String[] args) {
        logger.info("日志测试");

    }
}
