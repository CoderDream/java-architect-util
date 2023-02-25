package com.coderdream.easyexcelpractise.validator;


import com.keepsoft.microservice.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author wangwenbing
 * @email wangwenbing@163.com
 * @date 2017年11月4日 上午11:07:35
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RRException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws RRException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage())/*.append("<br>")*/;
                break;
            }

            throw new RRException(msg.toString());
        }
    }
}
