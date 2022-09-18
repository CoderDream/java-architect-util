package com.company.microserviceuser.exception;

import com.company.microserviceuser.enums.ResponseCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.company.microserviceuser.vo.common.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.microserviceuser.constant.CodeConstant.*;

/**
 * Uniform exception handle. 
 * @author xindaqi
 * @since 2020-10-21
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class) 
    public ResponseVO<String> validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errorMsg = "bindException";
        logger.error("数据绑定异常: {}", errorMsg);

        return new ResponseVO<String>().invalid(fieldErrors.get(0).getDefaultMessage());
        
    }

    /**
     * 方法参数校验异常,即@NotNull，@NotBlank
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseVO<String> methodValidationExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        logger.info("field error: {}", fieldErrors);
        String errorMsg = fieldErrors.toString();
        logger.error("方法入参异常: ", e);
        return new ResponseVO<String>().empty(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class) 
    public ResponseVO<Integer> exceptionHandler(NullPointerException e) {
            logger.error("空指针异常: ", e);
            return new ResponseVO<Integer>().exception(NULL_POINTER_EXCEPTION_CODE);
    }

    /**
     * 数据计算异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class) 
    public ResponseVO<Integer> exceptionHandler(ArithmeticException e) {
            logger.error("计算异常: ", e);
            return new ResponseVO<Integer>().exception(ARITHMETIC_EXCEPTION_CODE);
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class) 
    public ResponseVO<String> exceptionHandler(Exception e) {
            logger.error("异常: ", e);
            return new ResponseVO<String>().invalid(ResponseCodeEnums.INVALID.getMsg());
    }

    /**
     * 自定义异常
     *
     * @param myException
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public ResponseVO<String> exceptionHandler(MyException myException) {
        logger.error("自定义异常：", myException);
        return new ResponseVO<String>().fail(myException.getCode(), myException.getMessage());
    }
    
}