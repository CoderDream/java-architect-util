package com.debug.middleware.server.controller.lock;/**
 * Created by Administrator on 2019/4/20.
 */

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.controller.lock.dto.UserRegDto;
import com.debug.middleware.server.service.lock.UserRegService;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册请求Controller
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/20 20:23
 **/
@RestController
public class UserRegController {
    //定义日志实例
    private static final Logger log= LoggerFactory.getLogger(UserRegController.class);
    //定义请求的前缀
    private static final String prefix="user/reg";
    //定义处理用户注册请求的服务实例
    @Autowired
    private UserRegService userRegService;


    /**
     * 提交用户注册信息
     * @param dto
     * @return
     */
    @RequestMapping(value = prefix+"/submit",method = RequestMethod.GET)
    public BaseResponse reg(UserRegDto dto){
        //校验提交的用户名、密码等信息
        if (Strings.isNullOrEmpty(dto.getUserName()) || Strings.isNullOrEmpty(dto.getPassword())){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        //定义返回信息实例
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //处理用户提交请求-不加分布式锁
            //userRegService.userRegNoLock(dto);

            //处理用户提交请求-加分布式锁
            //userRegService.userRegWithLock(dto);

            //处理用户提交请求-加ZooKeeper分布式锁
            //userRegService.userRegWithZKLock(dto);

            //处理用户提交请求-加Redisson分布式锁
            userRegService.userRegRedisson(dto);
        }catch (Exception e){
            //发生异常情况的处理
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        //返回响应信息
        return response;
    }
}



















