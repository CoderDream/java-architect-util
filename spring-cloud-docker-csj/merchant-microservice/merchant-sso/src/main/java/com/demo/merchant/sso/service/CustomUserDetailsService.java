package com.demo.merchant.sso.service;

import com.demo.merchant.client.service.UserFuture;
import com.demo.merchant.object.UserQo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserFuture userFuture;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String json = userFuture.findByName(userName).join();
        UserQo userQo = new Gson().fromJson(json, UserQo.class);
        if (userQo == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return new SecurityUser(userQo);
    }
}
