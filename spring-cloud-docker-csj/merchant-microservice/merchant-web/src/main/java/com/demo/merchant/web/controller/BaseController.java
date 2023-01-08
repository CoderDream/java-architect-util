package com.demo.merchant.web.controller;

import com.demo.merchant.client.service.UserRestService;
import com.demo.merchant.object.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseController {
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private UserRestService userService;

    @Value("${spring.application.name}")
    private String serviceName;

    public List<ModelQo> getModels(String userName, HttpServletRequest request){
        //根据登录用户获取用户对象
        String json =  userService.findByName(userName);
        UserQo user = new Gson().fromJson(json, UserQo.class);

        //根据匹配分类获取模块（二级菜单）列表
        List<ModelQo> modelList = new ArrayList<>();
        List<Long> modelIds = new ArrayList<>();
        for(RoleQo role : user.getRoles()){
            for(ResourceQo resource : role.getResources()){
                String link = resource.getModel().getKind().getLink();//分类顶级菜单链接
                //获取模块列表，去重
                if(! modelIds.contains(resource.getModel().getId())
                        && pathMatcher.match(serviceName, link)){
                    modelList.add(resource.getModel());
                    modelIds.add(resource.getModel().getId());
                }
            }
        }

        return modelList;
    }

    public Long getMerchantId(String userName){
        Long merchantId = null;
        String json =  userService.findByName(userName);
        UserQo user = new Gson().fromJson(json, UserQo.class);
        if(user != null) merchantId = user.getMerchant().getId();
        return merchantId;
    }

    public MerchantQo getMerchantByUserName(String userName){
        String merchantId = null;
        String json =  userService.findByName(userName);
        UserQo user = new Gson().fromJson(json, UserQo.class);
        return user.getMerchant();
    }

}
