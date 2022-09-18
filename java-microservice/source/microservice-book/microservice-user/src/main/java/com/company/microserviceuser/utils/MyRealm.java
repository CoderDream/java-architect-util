package com.company.microserviceuser.utils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.service.*;

/**
 * Custom login password and username authentation.
 * @author xindaqi
 * @since 2020-11-08
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        LoginInputDTO params = new LoginInputDTO();
        params.setUsername(username);
        // params.setPassword();
        LoginOutputDTO loginOutputDTO = userService.login(params);
        String usernameInDb = loginOutputDTO.getUsername();
        String passwordInDb = loginOutputDTO.getPassword();
        if(!usernameInDb.equals(username)) {
            throw new UnknownAccountException("账号不存在");
        }
        return new SimpleAuthenticationInfo(username, passwordInDb, getName());
    }
}