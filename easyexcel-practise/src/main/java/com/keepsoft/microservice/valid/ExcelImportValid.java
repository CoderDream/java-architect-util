package com.keepsoft.microservice.valid;


import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p>Excel导入字段校验</p>
 *
 * created By DoLaLi on 2022/3/4
 */
public class ExcelImportValid {
    /**
     * Excel导入字段校验
     *
     * @param object 校验的JavaBean 其属性须有自定义注解
     */
    public static void valid(Object object) throws ExceptionCustom{
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            //设置可访问
            field.setAccessible(true);
            //属性的值
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                throw new ExceptionCustom("IMPORT_PARAM_CHECK_FAIL", "导入参数检查失败");
            }
            //是否包含必填校验注解
            boolean isExcelValid = field.isAnnotationPresent(ExcelValid.class);
            if (isExcelValid && Objects.isNull(fieldValue)) {
                throw new ExceptionCustom("NULL", field.getAnnotation(ExcelValid.class).message());
            }
        }
    }
}
