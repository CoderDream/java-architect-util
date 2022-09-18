package com.company.microserviceuser.controller;

import com.company.microserviceuser.dto.LoginInputDTO;
import com.company.microserviceuser.dto.LoginOutputDTO;
import com.company.microserviceuser.enums.MyCodeEnums;
import com.company.microserviceuser.enums.ResponseCodeEnums;
import com.company.microserviceuser.exception.MyException;
import com.company.microserviceuser.service.IUserService;
import com.company.microserviceuser.vo.common.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Shiro测试
 * @author xindaqi
 * @since 2021-01-16
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shiro")
@Api(tags = "Shiro验证")
public class ShiroTestController {

    private static final Logger logger = LoggerFactory.getLogger(ShiroTestController.class);

    @Resource
    private IUserService userService;

    @Resource
    private BCryptPasswordEncoder passwordEncoderCrypt;

    @PostMapping(value = "/doLogin")
    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "params", value = "用户信息", dataType = "LoginInputDTO", paramType = "body")
    public ResponseVO<String> login(@RequestBody @Valid LoginInputDTO params) throws MyException {
        Subject subject = SecurityUtils.getSubject();
        LoginOutputDTO loginOutputDTO = userService.login(params);

        if(null == loginOutputDTO) {
            throw new MyException(MyCodeEnums.USERNAME_PASSWORD_ERROR.getCode(), MyCodeEnums.USERNAME_PASSWORD_ERROR.getMsg());
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                params.getUsername(),
                params.getPassword()
        );
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            logger.info("未知用户：{}", e);
            throw new MyException(ResponseCodeEnums.USER_NAME_NOT_EXISTS.getCode(), ResponseCodeEnums.USER_NAME_NOT_EXISTS.getMsg());
        } catch (AuthenticationException e) {
            logger.info("用户名和密码错误: {}", e);
            throw new MyException(ResponseCodeEnums.USER_NAME_PASSWORD_ERROR.getCode(), ResponseCodeEnums.USER_NAME_PASSWORD_ERROR.getMsg());
        } catch (AuthorizationException e) {
            logger.info("未授权：{}", e);
            throw new MyException(ResponseCodeEnums.UNAUTHENTATION.getCode(), ResponseCodeEnums.UNAUTHENTATION.getMsg());
        }
        return new ResponseVO<String>().ok();
    }

    @PostMapping(value = "/test")
    @ApiOperation(value = "测试Shiro权限")
    public ResponseVO<String> test() throws MyException {
        return new ResponseVO<String>().ok();
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "测试Shiro登录默认路由")
    public ResponseVO<String> login() throws MyException {
        return new ResponseVO<String>().ok(ResponseCodeEnums.LOGIN.getMsg());
    }

}
