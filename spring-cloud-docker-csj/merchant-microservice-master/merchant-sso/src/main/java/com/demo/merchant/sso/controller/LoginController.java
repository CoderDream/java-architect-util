package com.demo.merchant.sso.controller;

import com.demo.merchant.client.service.UserFuture;
import com.demo.merchant.object.KindQo;
import com.demo.merchant.object.ResourceQo;
import com.demo.merchant.object.RoleQo;
import com.demo.merchant.object.UserQo;
import com.demo.merchant.sso.service.ImageCode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Controller
public class LoginController {
    @Autowired
    private UserFuture userFuture;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/signout")
    public String signout(HttpServletRequest request) throws Exception{
        request.logout();
        return "tologin";
    }

    @RequestMapping("/")
    public CompletableFuture<String> index(ModelMap model, Principal user) throws Exception{
        return userFuture.findByName(user.getName()).thenApply(json ->{
            UserQo userQo = new Gson().fromJson(json, UserQo.class);
            //分类列表（顶级菜单）
            List<KindQo> kindList = new ArrayList<>();
            List<Long> kindIds = new ArrayList<>();
            for(RoleQo roleQo : userQo.getRoles()){
                for(ResourceQo resourceVo : roleQo.getResources()){
                    //去重，获取分类列表
                    Long kindId = resourceVo.getModel().getKind().getId();
                    if(! kindIds.contains(kindId)){
                        kindList.add(resourceVo.getModel().getKind());
                        kindIds.add(kindId);
                    }
                }
            }

            model.addAttribute("kinds", kindList);
            model.addAttribute("user", user);
            return "home";
        });
    }


    @RequestMapping(value = "/images/imagecode")
    public String imagecode(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OutputStream os = response.getOutputStream();
        Map<String,Object> map = ImageCode.getImageCode(60, 20, os);

        String simpleCaptcha = "simpleCaptcha";
        request.getSession().setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
        request.getSession().setAttribute("codeTime",new Date().getTime());

        try {
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }

    @RequestMapping(value = "/checkcode")
    @ResponseBody
    public String checkcode(HttpServletRequest request, HttpSession session)
            throws Exception {
        String checkCode = request.getParameter("checkCode");
        Object simple = session.getAttribute("simpleCaptcha") ; //验证码对象
        if(simple == null){
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return "验证码已失效，请重新输入！";
        }

        String captcha = simple.toString();
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
        if(StringUtils.isEmpty(checkCode) || captcha == null ||  !(checkCode.equalsIgnoreCase(captcha))){
            request.setAttribute("errorMsg", "验证码错误！");
            return "验证码错误！";
        }else if ((now.getTime()-codeTime) / 1000 / 60 > 5){//验证码有效长度为5分钟
            request.setAttribute("errorMsg", "验证码已失效，请重新输入！");
            return "验证码已失效，请重新输入！";
        }else {
            session.removeAttribute("simpleCaptcha");
            return "1";
        }
    }


    @RequestMapping(value="/service/{name}")
    @ResponseBody
    public String getService(@PathVariable String name) {
        List<ServiceInstance> list = discoveryClient.getInstances(name);
        String serviceUri = "./";
        if(list != null && list.size() > 0){
            if(list.size() > 1) {
                Random random = new Random();
                ServiceInstance service = list.get(random.nextInt(list.size() - 1));
                serviceUri = service.getUri().toString();
            }else {
                ServiceInstance service = list.get(0);
                serviceUri = service.getUri().toString();
            }
        }
        return serviceUri;
    }
}
