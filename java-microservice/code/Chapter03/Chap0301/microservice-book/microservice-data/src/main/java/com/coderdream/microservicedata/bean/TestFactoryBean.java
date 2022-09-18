package com.coderdream.microservicedata.bean;


import com.coderdream.microservicedata.constant.StringConstant;
import lombok.Data;

/**
 * XmlBean实现FactoryBean
 * @author xindaqi
 * @since 2021-01-23
 */
@Data
public class TestFactoryBean {

    private int id;

    public String myXmlBean() {
        return StringConstant.XML_BEAN;
    }

}
