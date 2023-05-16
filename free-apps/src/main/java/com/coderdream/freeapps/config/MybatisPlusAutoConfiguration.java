package com.coderdream.freeapps.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2021/4/18 17:38
 * @description Mybatis plus 配置类
 */
//@ConditionalOnBean({com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration.class})
public class MybatisPlusAutoConfiguration {

//  @Bean
  public MetaObjectHandler metaObjectHandler() {
    return new FillFieldConfiguration();
  }

  public static class FillFieldConfiguration implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
      LocalDateTime now = LocalDateTime.now();
      metaObject.setValue("createdDate", now);
      metaObject.setValue("lastModifiedDate", now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
      metaObject.setValue("lastModifiedDate", LocalDateTime.now());
    }
  }

}
