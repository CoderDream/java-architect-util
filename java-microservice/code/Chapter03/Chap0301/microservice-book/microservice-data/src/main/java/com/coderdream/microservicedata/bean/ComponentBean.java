package com.coderdream.microservicedata.bean;

import com.coderdream.microservicedata.constant.StringConstant;
import org.springframework.stereotype.Component;

/**
 * Component注解创建Bean
 *
 * @author xindaqi
 * @since 2021-01-23
 */
@Component
public class ComponentBean {

    public String myComponentBean() {
        return StringConstant.COMPONENT_BEAN;
    }
}
