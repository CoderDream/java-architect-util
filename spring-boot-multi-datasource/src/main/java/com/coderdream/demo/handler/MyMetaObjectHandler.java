package com.coderdream.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j // 添加日志
//@Component // 注入Spring容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 添加日志
        log.info("start insert fill............");
        /**
         * String fieldName: 需要插入的字段
         * Object fieldVal: 需要插入的类型
         * MetaObject metaObject: 需要给那个数据处理
         */
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    /**
     * 更新时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("end update fill............");
        /**
         * 更新的时候只需要更新的字段
         */
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
