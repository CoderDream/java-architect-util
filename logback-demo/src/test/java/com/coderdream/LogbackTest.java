package com.coderdream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
    @Test
    public void test01() {
        Logger logger = LoggerFactory.getLogger(LogbackTest.class);
        logger.trace("trace追踪信息");
        logger.debug("debug详细信息"); // 默认级别
        logger.info("info关键信息");
        logger.warn("warn警告信息");
        logger.error("error错误信息");
    }
}
