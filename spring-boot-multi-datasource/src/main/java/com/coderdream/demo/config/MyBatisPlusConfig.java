package com.coderdream.demo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/8 16:52
 * ClassName:MyBatisPlusConfig
 * 类描述： Spring配置类
 */
// 表示扫描mapper包下的所有接口
@MapperScan("com.coderdream.demo..dao")
@EnableTransactionManagement // 开启自动事务
@Configuration // 表示这个类是全局配置类
public class MyBatisPlusConfig {

    /**
     * 注册乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页拦截器
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 开启 count 的 join 优化,只针对部分 left join
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除组件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev", "test"}) // 设置dev test环境开启,保证开发效率
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor =
                new PerformanceInterceptor();
        // 设置执行时间和写入日志
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        // 设置SQL执行时间和写入日志
        performanceInterceptor.setProperties(properties);

        // 设置SQL最大执行时间,如果超过了则不执行
        performanceInterceptor.setMaxTime(1);
        // 设置SQL是否格式化
        performanceInterceptor.setFormat(true);

        return performanceInterceptor;
    }
}
