package com.company.microserviceuser.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.microserviceuser.annotation.MyAnnotation;

/**
 * @author xindaqi
 * @since 2020-10-26
 */
@Component
public class TimeProcessUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeProcessUtil.class);

    /**
     *
     * @param timePattern
     * @return
     */
    @MyAnnotation
    public String timeGenerateFromPattern(String timePattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timePattern);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch(Exception e) {
            logger.error("时间异常", e);
        } finally {
            logger.info("时间转换");
        }
        
        return LocalDateTime.now().format(dtf);
    }
    
}