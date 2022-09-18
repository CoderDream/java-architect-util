package com.company.microservicedata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import com.company.microservicedata.annotation.MethodCostTimeStatisticAnnotation;
/**
 * 数据处理
 * 
 * @author xindaqi
 * @since 2021-01-12
 */
@Component
public class TimeProcessUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeProcessUtil.class);

    @MethodCostTimeStatisticAnnotation
    public String currentTimeString(String timeFormatter) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeFormatter);
        return LocalDateTime.now().format(dtf);
    }
    
}