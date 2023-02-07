package com.coderdream.demo.utils;

//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.baomidou.mybatisplus.generator.fill.Column;
//
//import java.util.*;

/**
 * @author juzi
 * @date 2022/8/1 14:50
 */
//public class CodeGenerator {
//    // 基础信息配置
//    /**
//     * 数据库连接字符
//     */
//    private static final String URL = "！！需要填写！！";
//
//    /**
//     * 示例：private static final String PARENT_PACKAGE_NAME = "com.example.mybatisplusdemo";
//     */
//    private static final String PARENT_PACKAGE_NAME = "！！需要填写！！";
//
//    /**
//     * 数据库用户名
//     */
//    private static final String USERNAME = "root";
//    /**
//     * 数据库密码
//     */
//    private static final String PASSWORD = "123456";
//    /**
//     * 项目根路径
//     */
//    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
//
//    /**
//     * 执行此处
//     */
//    public static void main(String[] args) {
//        // 简单示例，适用于单模块项目
//        simpleGenerator();
//        // 完整示例，适用于多模块项目
////        completeGenerator();
//    }
//
//    /**
//     * 【单模块】简单的实现方案
//     */
//    protected static void simpleGenerator() {
//
//        // 包路径
//        String packagePath = PROJECT_ROOT_PATH + "/src/main/java";
//        // XML文件的路径
//        String mapperXmlPath = PROJECT_ROOT_PATH + "/src/main/resources/mapper";
//
//        // 开始执行代码生成
//        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
//                // 1. 全局配置
//                .globalConfig(builder -> builder
//                        // 作者名称
//                        .author("橘子")
//                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。
//                        .fileOverride()
//                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
//                        .disableOpenDir()
//                        // 指定输出目录。如果指定，Windows生成至D盘根目录下，Linux or MAC 生成至 /tmp 目录下。
//                        .outputDir(packagePath)
//                        // 开启swagger2.注释掉则默认关闭。
//                        // .enableSwagger()
//                        // 指定时间策略。
//                        .dateType(DateType.TIME_PACK)
//                        // 注释时间策略。
//                        .commentDate("yyyy-MM-dd")
//                )
//
//                // 2. 包配置
//                .packageConfig((scanner, builder) -> builder
//                        // 设置父表名
//                        .parent(PARENT_PACKAGE_NAME)
//                        .moduleName(scanner.apply("请输入模块名："))
//                        // mapper.xml 文件的路径。单模块下，其他文件路径默认即可。
//                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperXmlPath))
//                )
//
//                // 3. 策略配置
//                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？生成所有表，请输入[all]：")))
//                        // 阶段1：Entity实体类策略配置
//                        .entityBuilder()
//                        // 开启生成实体时生成字段注解。
//                        // 会在实体类的属性前，添加[@TableField("nickname")]
//                        .enableTableFieldAnnotation()
//                        // 逻辑删除字段名(数据库)。
//                        .logicDeleteColumnName("is_delete")
//                        // 逻辑删除属性名(实体)。
//                        // 会在实体类的该字段属性前加注解[@TableLogic]
//                        .logicDeletePropertyName("isDelete")
//                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
//                        .addTableFills(new Column("create_time", FieldFill.INSERT))
//                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
//                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
//                        // 阶段2：Mapper策略配置
//                        .mapperBuilder()
//                        // 开启 @Mapper 注解。
//                        // 会在mapper接口上添加注解[@Mapper]
//                        .enableMapperAnnotation()
//                        // 启用 BaseResultMap 生成。
//                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
//                        .enableBaseResultMap()
//                        // 启用 BaseColumnList。
//                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
//                        .enableBaseColumnList()
//                        // 阶段4：Controller策略配置
//                        .controllerBuilder()
//                        // 会在控制类中加[@RestController]注解。
//                        .enableRestStyle()
//                        // 开启驼峰转连字符
//                        .enableHyphenStyle()
//                        .build()
//                )
//
//                // 4. 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
//                //.templateEngine(new BeetlTemplateEngine())
//                .templateEngine(new FreemarkerTemplateEngine())
//
//                // 5. 执行
//                .execute();
//    }
//
//    /**
//     * 【多模块使用】完整的实现方案
//     */
//    protected static void completeGenerator() {
//        //【1】基础信息配置
//        // 指定模块名，用于生成的java文件的import。
//        String moduleName = scanner();
//        // 六个文件的路径。多模块项目下，一般来说每个文件的路径都是不同的（根据项目实际，可能在不同的模块下）。
//        String entityPath = PROJECT_ROOT_PATH + "/project-entity/src/main/java/com/yourdomain/projectname/entity/" + moduleName;
//        String mapperPath = PROJECT_ROOT_PATH + "/project-dao/src/main/java/com/yourdomain/projectname/mapper/" + moduleName;
//        String mapperXmlPath = PROJECT_ROOT_PATH + "/project-dao/src/main/resources/mapper/" + moduleName;
//        String servicePath = PROJECT_ROOT_PATH + "/project-service/src/main/java/com/yourdomain/projectname/service/" + moduleName;
//        String serviceImplPath = PROJECT_ROOT_PATH + "/project-service/src/main/java/com/yourdomain/projectname/service/" + moduleName + "/impl";
//        String controllerPath = PROJECT_ROOT_PATH + "/project-controller/src/main/java/com/yourdomain/projectname/controller/" + moduleName;
//        // 关于以上写法的解释：
//        // --- 假设我们的项目有四个模块：project-entity、project-dao、project-service、project-controller
//        // --- project-entity 的包路径：com.yourdomain.projectname.eneity，
//        //   ---则生成system模块下的sys_user表，生成的实体文件将放在：com.yourdomain.projectname.entity.system包下，SysUser.java。
//        // --- project-dao 的包路径：com.yourdomain.projectname.mapper，
//        //   ---则生成system模块下的sys_user表，生成的mapper接口文件将放在：com.yourdomain.projectname.mapper.system包下，类名为：SysUserMapper.java。
//        // --- 其他文件以此类推，即每个模块放MVC结构中对应的类型文件。
//        // --- 注意：这里最后的文件路径修改了，下文配置中的【2 包配置】中的包路径也要同步修改！否则生成的java文件，首句import会报错。原因是路径错误。
//
//        // 【2】开始执行代码生成
//        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
//                // 1. 全局配置
//                .globalConfig(builder -> builder
//                        // 作者名称
//                        .author("拾年之璐")
//                        // 开启覆盖已生成的文件。注释掉则关闭覆盖。请谨慎开启此选项！
//                        // .fileOverride()
//                        // 禁止打开输出目录。注释掉则生成完毕后，自动打开生成的文件目录。
//                        .disableOpenDir()
//                        // 指定输出目录。多模块下，每个类型的文件输出目录不一致，在包配置阶段配置。
//                        // .outputDir(packagePath)
//                        // 开启swagger2。注释掉则默认关闭。
//                        // .enableSwagger()
//                        // 开启 kotlin 模式。注释掉则关闭此模式
//                        // .enableKotlin()
//                        // 指定时间策略。
//                        .dateType(DateType.TIME_PACK)
//                        // 注释时间策略。
//                        .commentDate("yyyy-MM-dd")
//                )
//
//                // 2. 包配置
//                .packageConfig((scanner, builder) -> builder
//                        // 阶段1：各个文件的包名设置，用来拼接每个java文件的第一句：package com.XXX.XXX.XXX.xxx;
//                        // 父包名配置
//                        .parent(PARENT_PACKAGE_NAME)
//                        // 输入模块名。此模块名会在下面的几个包名前加。多模块项目，请根据实际选择是否添加。
//                        // .moduleName(moduleName)
//                        .entity("entity." + moduleName)
//                        .mapper("mapper." + moduleName)
//                        .service("service." + moduleName)
//                        .serviceImpl("service." + moduleName + ".impl")
//                        .controller("controller." + moduleName)
//                        .other("other")
//                        // 阶段2：所有文件的生成路径配置
//                        .pathInfo(
//                                new HashMap<OutputFile, String>() {{
//                                    // 实体类的保存路径
//                                    put(OutputFile.entity, entityPath);
//                                    // mapper接口的保存路径
//                                    put(OutputFile.mapper, mapperPath);
//                                    // mapper.xml文件的保存路径
//                                    put(OutputFile.mapperXml, mapperXmlPath);
//                                    // service层接口的保存路径
//                                    put(OutputFile.service, servicePath);
//                                    // service层接口实现类的保存路径
//                                    put(OutputFile.serviceImpl, serviceImplPath);
//                                    // 控制类的保存路径
//                                    put(OutputFile.controller, controllerPath);
//                                }}
//                        )
//                )
//
//                // 3. 策略配置【请仔细阅读每一行，根据项目实际项目需求，修改、增删！！！】
//                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？生成所有表，请输入[all]：")))
//                        // 阶段1：Entity实体类策略配置
//                        .entityBuilder()
//                        // 设置父类。会在生成的实体类名后：extends BaseEntity
//                        // .superClass(BaseEntity.class)
//                        // 禁用生成 serialVersionUID。（不推荐禁用）
//                        // .disableSerialVersionUID()
//                        // 开启生成字段常量。
//                        // 会在实体类末尾生成一系列 [public static final String NICKNAME = "nickname";] 的语句。（一般在写wapper时，会用到）
//                        // .enableColumnConstant()
//                        // 开启链式模型。
//                        // 会在实体类前添加 [@Accessors(chain = true)] 注解。用法如 [User user=new User().setAge(31).setName("snzl");]（这是Lombok的注解，需要添加Lombok依赖）
//                        // .enableChainModel()
//                        // 开启 lombok 模型。
//                        // 会在实体类前添加 [@Getter] 和 [@Setter] 注解。（这是Lombok的注解，需要添加Lombok依赖）
//                        // .enableLombok()
//                        // 开启 Boolean 类型字段移除 is 前缀。
//                        // .enableRemoveIsPrefix()
//                        // 开启生成实体时生成字段注解。
//                        // 会在实体类的属性前，添加[@TableField("nickname")]
//                        .enableTableFieldAnnotation()
//                        // 逻辑删除字段名(数据库)。
//                        .logicDeleteColumnName("is_delete")
//                        // 逻辑删除属性名(实体)。
//                        // 会在实体类的该字段属性前加注解[@TableLogic]
//                        .logicDeletePropertyName("isDelete")
//                        // 数据库表映射到实体的命名策略(默认下划线转驼峰)。一般不用设置
//                        // .naming(NamingStrategy.underline_to_camel)
//                        // 数据库表字段映射到实体的命名策略(默认为 null，未指定按照 naming 执行)。一般不用设置
//                        // .columnNaming(NamingStrategy.underline_to_camel)
//                        // 添加父类公共字段。
//                        // 这些字段不会出现在新增的实体类中。
//                        .addSuperEntityColumns("id", "delete_time")
//                        // 添加忽略字段。
//                        // 这些字段不会出现在新增的实体类中。
//                        // .addIgnoreColumns("password")
//                        // 添加表字段填充
//                        // 会在实体类的该字段上追加注解[@TableField(value = "create_time", fill = FieldFill.INSERT)]
//                        .addTableFills(new Column("create_time", FieldFill.INSERT))
//                        // 会在实体类的该字段上追加注解[@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)]
//                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
//                        // 全局主键类型。如果MySQL主键设置为自增，则不需要设置此项。
//                        // .idType(IdType.AUTO)
//                        // 格式化文件名称。
//                        // 如果不设置，如表[sys_user]的实体类名是[SysUser]。设置成下面这样，将是[SysUserEntity]
//                        // .formatFileName("%sEntity")
//
//                        // 阶段2：Mapper策略配置
//                        .mapperBuilder()
//                        // 设置父类
//                        // 会在mapper接口方法集成[extends BaseMapper<XXXEntity>]
//                        // .superClass(BaseMapper.class)
//                        // 开启 @Mapper 注解。
//                        // 会在mapper接口上添加注解[@Mapper]
//                        .enableMapperAnnotation()
//                        // 启用 BaseResultMap 生成。
//                        // 会在mapper.xml文件生成[通用查询映射结果]配置。
//                        .enableBaseResultMap()
//                        // 启用 BaseColumnList。
//                        // 会在mapper.xml文件生成[通用查询结果列 ]配置
//                        .enableBaseColumnList()
//                        // 设置缓存实现类
//                        // .cache(MyMapperCache.class)
//                        // 格式化 mapper 文件名称。
//                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper]。写成下面这种形式后，将变成[SysUserDao]。
//                        // .formatMapperFileName("%sDao")
//                        // 格式化 xml 实现类文件名称。
//                        // 如果不设置，如表[sys_user]，默认的文件名是[SysUserMapper.xml]，写成下面这种形式后，将变成[SysUserXml.xml]。
//                        // .formatXmlFileName("%sXml")
//
//                        // 阶段3：Service策略配置
//                        // .serviceBuilder()
//                        // 设置 service 接口父类
//                        // .superServiceClass(BaseService.class)
//                        // 设置 service 实现类父类
//                        // .superServiceImplClass(BaseServiceImpl.class)
//                        // 格式化 service 接口文件名称
//                        // 如果不设置，如表[sys_user]，默认的是[ISysUserService]。写成下面这种形式后，将变成[SysUserService]。
//                        // .formatServiceFileName("%sService")
//                        // 格式化 service 实现类文件名称
//                        // 如果不设置，如表[sys_user]，默认的是[SysUserServiceImpl]。
//                        // .formatServiceImplFileName("%sServiceImpl")
//
//                        // 阶段4：Controller策略配置
//                        .controllerBuilder()
//                        // 设置父类。
//                        // 会集成此父类。
//                        // .superClass(BaseController.class)
//                        // 开启生成 @RestController 控制器
//                        // 会在控制类中加[@RestController]注解。
//                        .enableRestStyle()
//                        // 开启驼峰转连字符
//                        .enableHyphenStyle()
//
//                        // 最后：构建
//                        .build()
//                )
//
//                //模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
//                //.templateEngine(new BeetlTemplateEngine())
//                .templateEngine(new FreemarkerTemplateEngine())
//
//                // 执行
//                .execute();
//    }
//
//    // 处理 all 情况
//    protected static List<String> getTables(String tables) {
//        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
//    }
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    private static String scanner() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入" + "请输入模块名：" + "：");
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StrUtil.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + "请输入模块名：" + "！");
//    }


//}
//
//package com.kyplatform.generator;

        import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
        import com.baomidou.mybatisplus.core.toolkit.StringUtils;
        import com.baomidou.mybatisplus.generator.AutoGenerator;
        import com.baomidou.mybatisplus.generator.config.*;
        import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

        import java.util.Scanner;

/**
 * mybatis代码生成器
 */
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");//生成文件的输出目录
        gc.setAuthor("zhicaili");//开发人员
        gc.setOpen(true);//是否打开输出目录
        gc.setServiceName("%sService");//service 命名方式
        gc.setServiceImplName("%sServiceImpl");//service impl 命名方式
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.3.4:33016/object_data?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public"); 数据库 schema name
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));//父包模块名
        pc.setParent("com.coderdream.admin");//父包名。// 自定义包路径  如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");//设置控制器包名
        mpg.setPackageInfo(pc);

        // 自定义配置
/*        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });*/
 /*       cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        //  strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");//自定义继承的Entity类全称，带包名
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");//自定义继承的Controller类全称，带包名
//        strategy.setInclude("tb_user","tb_organization","tb_person","tb_signin","tb_sys_config","tb_sys_log");//需要包含的表名，允许正则表达式
        strategy.setInclude("st_stbprp_b");//需要包含的表名，允许正则表达式
        //strategy.setSuperEntityColumns("id");//自定义基础的Entity类，公共字段
        strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符
        strategy.setTablePrefix("tb_");//表前缀
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
