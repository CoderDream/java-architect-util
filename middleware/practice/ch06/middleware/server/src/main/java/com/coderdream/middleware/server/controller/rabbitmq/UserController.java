package com.coderdream.middleware.server.controller.rabbitmq;

import com.coderdream.middleware.api.enums.StatusCode;
import com.coderdream.middleware.api.response.BaseResponse;
import com.coderdream.middleware.server.dto.UserLoginDto;
import com.coderdream.middleware.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户登录controller
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:05
 **/
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String prefix = "user";

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = prefix + "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse login(@RequestBody @Validated UserLoginDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            Boolean res = userService.login(dto);
            if (res) {
                response = new BaseResponse(StatusCode.Success.getCode(), "登录成功");
            } else {
                response = new BaseResponse(StatusCode.Fail.getCode(), "登录失败-账户名密码不匹配");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}