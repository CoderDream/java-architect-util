package com.company.microserviceuser.utils;

import com.company.microserviceuser.annotation.MyAnnotation;
import com.company.microserviceuser.annotation.Desensitization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

import static com.company.microserviceuser.constant.StringConstant.DESENSITIZATION_PSD;

/**
 * 注解工具
 * @author xindaqi
 * @since 2020-12-26
 */
@Component
public class AnnotationUtil {

    private static Logger logger = LoggerFactory.getLogger(AnnotationUtil.class);

    @MyAnnotation(name = "daqi")
    public String annotationTestUnderMethod() {
        logger.info("使用注解的方法");
        return "annotation under method";
    }

    public Object desensitization(Object object) {
        try {

            Class<?> clzz = object.getClass();
            Field[] fields = clzz.getDeclaredFields();
            for(Field field : fields) {
                if(field.isAnnotationPresent(Desensitization.class)) {
                    field.setAccessible(true);
                    field.set(object, DESENSITIZATION_PSD);
                }
            }
            return object;

        } catch (Exception e) {
            logger.info("属性脱敏异常");

        }
        
        return object;
    }

}
