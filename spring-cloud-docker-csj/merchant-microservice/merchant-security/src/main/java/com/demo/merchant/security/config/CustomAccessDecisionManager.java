package com.demo.merchant.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;


public class CustomAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }

        //从CustomSecurityMetadataSource(getAttributes)中获取请求资源所需的角色集合
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();

        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //对资源访问具有权限的角色
            String needRole = configAttribute.getAttribute();
            logger.info("具有权限的角色：" + needRole);
            //在用户拥有的权限中检查是否具有匹配的角色
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //如果所有用户角色都不匹配，则用户没有权限
        throw new AccessDeniedException("Cannot Access!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
