package com.company.microserviceuser.config;

import com.company.microserviceuser.enums.ResponseCodeEnums;
import com.company.microserviceuser.exception.MyException;
import com.company.microserviceuser.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截器
 * @author xindaqi
 * @since 2021-01-15
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");
        if(null == token || token.isEmpty()) {
            throw new MyException(ResponseCodeEnums.TOKEN_EMPTY.getCode(), ResponseCodeEnums.TOKEN_EMPTY.getMsg());
        }
        logger.info("Token: {}", token);
        boolean verifyFlag = JwtUtil.tokenVerify(token);
        logger.info("验证结果：{}", verifyFlag);
        if(verifyFlag) {
            logger.info("成功--进入请求");
            return true;
        }
        logger.info("失败--进入请求");
        throw new MyException(ResponseCodeEnums.TOKEN_INVALID.getCode(), ResponseCodeEnums.TOKEN_INVALID.getMsg());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        logger.info("完成请求");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
        logger.info("请求结束--什么也不做");
    }
}
