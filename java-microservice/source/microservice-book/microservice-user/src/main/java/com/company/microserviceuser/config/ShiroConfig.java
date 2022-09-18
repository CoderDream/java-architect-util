package com.company.microserviceuser.config;

import com.company.microserviceuser.utils.*;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author xindaqi
 * @since 2020-10-08
 */
@Configuration
public class ShiroConfig {

    @Bean 
    MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        myRealm.setAuthenticationTokenClass(AuthenticationToken.class);
        myRealm.setCredentialsMatcher(new MyCredentialsMatcherUtil());
        return myRealm;
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager; 
    }

    @Bean 
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/shiro/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/shiro/doLogin", "anon");
        /**
         * Swagger2
         */
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/v2/**", "anon");
        map.put("/webjars/**", "anon");
        // map.put("/configuration/security", "anon");
        // map.put("/configuration/ui", "anon");
        
        map.put("/api/**", "anon");
        map.put("/**", "authc");

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    
}