package com.company.microserviceuser.utils;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author xindaqi
 * @since 2020-10-11
 */
public class MyCredentialsMatcherUtil extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    // Password from user login. 
    String originalPassword = String.valueOf((char[]) token.getCredentials());
    // Password in database
    String sqlOriginalPassword=(String)info.getCredentials();
    BCryptPasswordEncoder pwdCmp = new BCryptPasswordEncoder();
    return pwdCmp.matches(originalPassword,sqlOriginalPassword);
    }

}