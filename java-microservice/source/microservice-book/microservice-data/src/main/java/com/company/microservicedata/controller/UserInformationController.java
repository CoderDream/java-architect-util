package com.company.microservicedata.controller;

import com.company.microservicedata.dto.MessageQueueInputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.microservicedata.service.*;
import com.company.microservicedata.feign.*;
import com.company.microservicedata.dto.*;
import com.company.microservicedata.vo.*;
import com.company.microservicedata.vo.common.*;
import com.company.microservicedata.enums.*;
import com.company.microservicedata.util.ExcelProcessUtil;
import com.company.microservicedata.constant.StringConstant;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.microservicedata.util.TimeProcessUtil;

/**
 * Feign call test.
 * @author xindaqi
 * @since 2020-12-02
 */
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/v1/data")
@Api(tags = "User Feign")
public class UserInformationController {

    private static Logger logger = LoggerFactory.getLogger(UserInformationController.class);


    @Autowired
    private IUserModuleFeign userModuleFeign;

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    private ExcelProcessUtil excelProcessUtil;
    
    @Autowired
    private TimeProcessUtil timeProcessUtil;

    @RequestMapping(value="/user/page", method=RequestMethod.POST)
    @ApiOperation("分页查询用户信息")
    public ResponseVO<List<UserDetailsVO>> queryUserByPage(@RequestBody QueryUserByPageInputDTO params) {
        return userModuleFeign.queryUserByPage(params);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ApiOperation("下载Excel")
    public void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        String downloadLink = userInformationService.downloadExcel(request);
        Map<String, String> map = new HashMap<>();
        map.put("downloadLink", downloadLink);
        String respBody = JSON.toJSONString(map);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setLocale(new java.util.Locale("zh","CN"));
            response.setHeader("content-Type", "application/json");
            response.getWriter().write(respBody);
            logger.info("下载-成功");
        } catch(Exception e) {
            logger.error("下载-失败");
        }

    }

    @RequestMapping(value = "/annotation/test", method = RequestMethod.GET) 
    @ApiOperation("测试方法级别注解")
    public String methodAnnotationTest() {
        int sum = userInformationService.add(1, 2);
        return timeProcessUtil.currentTimeString(StringConstant.YYYYMMDDHHMMSS);
    }



}