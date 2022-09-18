package com.company.microservicedata.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * XmlBean实现FactoryBean
 * @author xindaqi
 * @since 2021-01-23
 */

public class XmlFactoryBean implements FactoryBean<TestFactoryBean> {

    @Override
    public TestFactoryBean getObject() throws Exception {
        return new TestFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.class;
    }

    public String beanMethodTest() {
        return "FactoryBean";
    }
}
