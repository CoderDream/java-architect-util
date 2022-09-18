package com.company.microservicedata.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 方法耗时统计注解实现
 * 
 * @author xindaqi
 * @since 2021-01-12
 */
@Aspect
@Component
public class MethodCostTimeStatisticAop {

    private static final Logger logger = LoggerFactory.getLogger(MethodCostTimeStatisticAop.class);

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Around("@annotation(com.company.microservicedata.annotation.MethodCostTimeStatisticAnnotation)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("方法：{}开始执行", methodName);
        startTime.set(System.currentTimeMillis());
        Object obj = proceedingJoinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime.get();
        logger.info("方法：{}执行结束,执行耗时：{}ms", methodName, costTime);
        startTime.remove();
        return obj;

    }
}