package com.company.microservicedata;

// import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.company.microservicedata.bean.*;
import com.company.microservicedata.constant.StringConstant;
import com.company.microservicedata.util.ExcelProcessUtil;
import com.company.microservicedata.vo.UserDetailsVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author xindaqi
 * @since 2020-12-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceDataApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(MicroserviceDataApplicationTests.class);

    @Autowired
    private ExcelProcessUtil excelProcessUtil;

    @Test
    public void contextLoads() {
        logger.info("Test test.");

    }

    /**
     * 测试文件下载
     *
     * @author xindaqi
     * @since 2020-12-17
     */
    @Test
    public void downloadExcel() {
        try {
            excelProcessUtil.mergeTableTest();
        } catch (Exception e) {
            logger.error("下载Excel失败");
        }

    }


    /**
     * 测试文件下载XSSF模式
     *
     * @author xindaqi
     * @since 2020-12-17
     */
    @Test
    public void downloadExcelXSSF() {
        try {
            excelProcessUtil.mergeTestWithXSSF();
        } catch (Exception e) {
            logger.error("下载Excel失败");
        }

    }

    @Test
    public void jsonStringToObject() {
        String userStr = "{\n" +
                "    \"id\":1,\n" +
                "    \"username\":\"xiaoxiao\",\n" +
                "    \"sex\":\"女\",\n" +
                "    \"address\":\"中国\"\n" +
                "}";
        UserDetailsVO userDetailsVO = JSON.parseObject(userStr, new TypeReference<UserDetailsVO>() {
        });
        logger.info("我是字符串转换后的对象：{}", userDetailsVO);
    }

    @Autowired
    private ComponentBean componentBean;

    /**
     * Component创建Bean
     */
    @Test
    public void componentBeanTest() {
        String componentBeanStr = componentBean.myComponentBean();
        Assert.assertEquals(StringConstant.COMPONENT_BEAN, componentBeanStr);
        logger.info("Bean:{}", componentBeanStr);
    }

    @Autowired
    private ConfigurationBean configurationBean;

    /**
     * Configuration创建Bean
     */
    @Test
    public void configurationBeanTest() {
        String configurationBeanStr = configurationBean.myConfigurationBean();
        Assert.assertEquals(StringConstant.CONFIGURATION_BEAN, configurationBeanStr);
        logger.info("Bean:{}", configurationBeanStr);
    }

    @Autowired
    private XmlBean xmlBean;

    /**
     * Xml创建Bean
     */
    @Test
    public void xmlBeanTest() {
        String xmlBeanStr = xmlBean.myXmlBean();
        Assert.assertEquals(StringConstant.XML_BEAN, xmlBeanStr);
        logger.info("Bean:{}", xmlBeanStr);
    }

    /**
     * 通过xml获取Bean
     */
    @Test
    public void getBeanByXml() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:xml/applicationContext.xml");
        XmlBean xmlBean = (XmlBean) ac.getBean("xmlBeanTest");
        String xmlBeanStr = xmlBean.myXmlBean();
        Assert.assertEquals(StringConstant.XML_BEAN, xmlBeanStr);
        logger.info("Bean:{}", xmlBeanStr);

    }

    /**
     * 获取Java配置类的Bean
     */
    @Test
    public void getBeanByConfiguration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConfigurationBean.class);
        ctx.refresh();
        String beanStr = (String) ctx.getBean("myConfigurationBean");
        Assert.assertEquals(StringConstant.CONFIGURATION_BEAN, beanStr);
        logger.info("Bean:{}", beanStr);
        ctx.close();

    }

    /**
     * 获取注解的Bean
     */
    @Test
    public void getBeanByAnnotation() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ComponentBean.class);
        ctx.refresh();
        ComponentBean componentBean = ctx.getBean(ComponentBean.class);
        String beanStr = componentBean.myComponentBean();
        Assert.assertEquals(StringConstant.COMPONENT_BEAN, beanStr);
        logger.info("Bean:{}", beanStr);
        ctx.close();
    }

    /**
     * 从classpath获取Xml的Bean
     */
    @Test
    public void getBeanByClasspath() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:xml/applicationContext.xml");
        XmlBean xmlBean = (XmlBean) ctx.getBean("xmlBeanTest");
        String beanStr = xmlBean.myXmlBean();
        Assert.assertEquals(StringConstant.XML_BEAN, beanStr);
        logger.info("Bean:{}", beanStr);
    }


    /**
     * 通过xml获取Bean
     */
    @Test
    public void getFactoryBeanByXml() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:xml/applicationContext.xml");
        TestFactoryBean xmlBean = (TestFactoryBean) ac.getBean("xmlFactoryBean");
        String xmlBeanStr = xmlBean.myXmlBean();
        XmlFactoryBean xmlFactoryBean = (XmlFactoryBean) ac.getBean("&xmlFactoryBean");
        String xmlFactoryBeanStr = xmlFactoryBean.beanMethodTest();
        Assert.assertEquals(StringConstant.XML_BEAN, xmlBeanStr);
        logger.info("Bean:{}", xmlBeanStr);
        logger.info("Factory bean: {}", xmlFactoryBeanStr);

    }


}
