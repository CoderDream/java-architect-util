package com.feng.echartsUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @ClassName FreemarkerUtil
 * @Description TODO
 * @Author admin
 * @Date 2021/4/6 14:33
 * @Version 1.0
 */
public class FreemarkerUtil {

    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("").getPath();

    /**
     * @return java.lang.String
     * @Author fengfanli
     * @Description //TODO 通过 freemarker 将 echarts 模板文件 option.ftl  生成字符串
     * @Date 15:44 2021/4/8
     * @Param [templateFileName, templateDirectory, datas]
     **/
    public static String generateString(String templateFileName, String templateDirectory, Map<String, Object> datas)
            throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 设置默认编码
        configuration.setDefaultEncoding("UTF-8");
        // 设置模板所在文件夹
        File dir = new File(path + templateDirectory);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        configuration.setDirectoryForTemplateLoading(dir);
        // 生成模板对象
        Template template = configuration.getTemplate(templateFileName);
        // 将datas写入模板并返回
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(datas, stringWriter);
            stringWriter.flush();
            return stringWriter.getBuffer().toString();
        }
    }
}
