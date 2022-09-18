package com.company.microserviceuser.aop;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.company.microserviceuser.utils.AnnotationUtil;

import org.aspectj.lang.JoinPoint; 
import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.stream.Stream;

import com.company.microserviceuser.dto.LoginInputDTO;
/**
 * 自定义方法切面.
 *  
 * @author xindaqi 
 * @since 2021-01-11
 */
@Aspect 
@Component
public class MyAop {

    @Resource
    private AnnotationUtil annotationUtil;

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(ApiCallLog.class);

    @Around("@annotation(com.company.microserviceuser.annotation.MyAnnotation)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        // 方法执行前
        logger.info("===我是使用了MyAnnotation注解的方法：{}===", methodName);
        startTime.set(System.currentTimeMillis());
        Object obj = joinPoint.proceed();
        // 方法执行后
        long costTime = System.currentTimeMillis() - startTime.get();
        startTime.remove();
        logger.info("方法：{}执行时间:{}ms", methodName, costTime);
        return obj;
    }   
    
}
