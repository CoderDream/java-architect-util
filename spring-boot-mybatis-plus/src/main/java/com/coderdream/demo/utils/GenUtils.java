package com.coderdream.demo.utils;

//

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/9 11:38
 * ClassName:MoBaiCode
 * 类描述： MybatisPlus代码生成器
 */
public class GenUtils {
    public static void main(String[] args) {
        // MybatisPlus代码生成器必须要new一个AutoGenerator对象
        AutoGenerator mpg = new AutoGenerator();
        /**
         * 配置策略
         * 1.全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        // 获取当前资源路径
        String projectPath = System.getProperty("user.dir");
        // 生成的代码输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        // 生成作者信息
        gc.setAuthor("MoBai·杰");
        // 是否打开输出目录
        gc.setOpen(false);
        // 是否覆盖文件
        gc.setFileOverride(false);
        // 取消生成以后I前缀
        gc.setServiceName("%sService");
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.ID_WORKER);
        // 只使用 java.util.date 代替
        gc.setDateType(DateType.ONLY_DATE);
        // 开启 swagger2 模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        /**
         * 2.配置数据源相关
         * DataSourceConfig:数据库配置
         */
        DataSourceConfig ds = new DataSourceConfig();
//        ds.setDriverName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://192.168.3.4:33016/object_data??useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");

        ds.setDriverName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.3.4:33016/object_data?characterEncoding=utf-8");

        ds.setUsername("root");
        ds.setPassword("123456");
        // 数据库类型
        ds.setDbType(DbType.MYSQL);
        mpg.setDataSource(ds);

        /**
         * 3.设置包的配置
         */
        PackageConfig pc = new PackageConfig();
        // 项目名称
        pc.setModuleName("cxdz");
        // 项目路径
        pc.setParent("com.coderdream");
        // 实体类包名
        pc.setEntity("pojo");
        // Dao包名
        pc.setMapper("dao");
        // service包名
        pc.setService("service");
        // service实现类包名
        pc.setServiceImpl("service.impl");
        // controller包名
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        /**
         * 4.策略配置
         */
        StrategyConfig sc = new StrategyConfig();
        // 需要映射的表
        sc.setInclude("st_stbprp_b");
        // 从数据库表到文件的命名策略/下划线转驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否为lombok模型/默认false
        sc.setEntityLombokModel(true);
        // 设置逻辑删除字段
        sc.setLogicDeleteFieldName("deleted");

        /**
         * 5.设置自动填充
         */
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(gmtCreate);
        list.add(gmtModified);
        // 表填充字段
        sc.setTableFillList(list);
        // 设置乐观锁字段
        sc.setVersionFieldName("version");
        // 生成@RestController控制器
        sc.setRestControllerStyle(true);
        // 驼峰转连字符
        sc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sc);
        // 执行
        mpg.execute();
    }
}
