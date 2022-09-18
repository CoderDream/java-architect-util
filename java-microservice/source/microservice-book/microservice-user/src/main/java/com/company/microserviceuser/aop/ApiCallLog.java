package com.company.microserviceuser.aop;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.company.microserviceuser.utils.AnnotationUtil;

import org.aspectj.lang.JoinPoint; 
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect; 
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.stream.Stream;

import com.company.microserviceuser.dto.LoginInputDTO;
/**
 * 日志拦截.
 *  
 * @author xindaqi 
 * @since 2020-10-23
 */
@Aspect 
@Component
public class ApiCallLog {

    @Resource
    private AnnotationUtil annotationUtil;

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(ApiCallLog.class);

    /**
     * 方法切入点，执行对应的方法
     * 
     * 1. execution : 表达式主体
     * 2. * :任意类型返回值
     * 3. com.company.web.controller : AOP切片的包名
     * 4. .. :当前包及子包
     * 5. * : 类名,*表示所有类
     * 6. .*(..) : 方法名,*表示任何方法名,..:任何参数类型
     */
    @Pointcut("execution(* com.company.microserviceuser.controller..*.*(..)) || execution(* com.company.microserviceuser.exception..*.*(..))")
    public void callLog() {
        logger.info("Pointcut");
    }

    /**
     * 切入方法执行前的方法
     * 
     * @param joinPoint 反射接口（Interface）
     * @throws Exception
     */
    @Before("callLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info("===Api call aspect do before.===");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL: {}", request.getRequestURI());
        logger.info("HTTP METHOD: {}", request.getMethod());
        logger.info("IP: {}", request.getRemoteAddr());
        logger.info("Class METHOD: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("Class name:{}", joinPoint.getTarget().getClass().getName());
        Enumeration<String> enums = request.getParameterNames();
        while(enums.hasMoreElements()) {
            String paramName = enums.nextElement();
            logger.info("Parameter name: {}", request.getParameter(paramName));
        }
    }

    /**
     * 切入方法执行结束后的方法
     * 
     * @param joinPoint 反射接口（Interface）
     */
    @After("callLog()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("===Api call aspect do after.===");

    }

    /**
     * 切入方法返回后执行方法
     * 
     * @param joinPoint 反射接口（Interface）
     */
    @AfterReturning(value = "callLog()", returning = "returnValue")
    public void doAfterReturing(JoinPoint joinPoint, Object returnValue) throws Exception {
        if(joinPoint.getArgs().length != 0){
            Stream.of(joinPoint.getArgs()).forEach(s -> annotationUtil.desensitization(s));
            annotationUtil.desensitization(joinPoint.getArgs()[0]);
        }
        logger.info("ARGS: {}", joinPoint.getArgs());
        long costTime = System.currentTimeMillis() - startTime.get();
        logger.info("返回值：{}", returnValue);
        logger.info("Time spending: {}ms", costTime);
        logger.info("===Api call aspect do afterReturing.===");
        startTime.remove();
    }

    
    
}