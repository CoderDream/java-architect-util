package com.coderdream.mybatisplusdemo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        // 获取当前项目地址
        String dir = System.getProperty("user.dir");
        System.out.println(dir); // 192.168.3.4:33016
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false",
                        "root", "123456")
                .globalConfig(builder -> { //全局配置
                    builder.author("CoderDream") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(dir + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.coderdream") // 设置父包名
                            .moduleName("mybatisplusdemo") // 设置父包模块名  生成的内容都在com.guo.mybatisplus.mybatisplusAuto包下
                            .pathInfo(Collections.singletonMap(OutputFile.xml, dir + "\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                    //                            .pathInfo(Collections.singletonMap(OutputFile.xml, dir + "\\src\\main\\java")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> { //策略配置 根据哪个表，逆向生成
//                    builder.addInclude("t_user") // 设置需要生成的表名
                    builder.addInclude("t_demo_copy") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(); //执行
    }
}
