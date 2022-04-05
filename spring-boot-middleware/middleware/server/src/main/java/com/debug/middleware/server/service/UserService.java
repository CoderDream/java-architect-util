package com.debug.middleware.server.service;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.model.entity.User;
import com.debug.middleware.model.mapper.UserMapper;
import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.rabbitmq.publisher.LogPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:12
 **/
@Service
public class UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogPublisher logPublisher;


    /**
     * 用户登录服务
     * @param dto
     * @return
     * @throws Exception
     */
    public Boolean login(UserLoginDto dto) throws Exception{
        User user=userMapper.selectByUserNamePassword(dto.getUserName(),dto.getPassword());
        if (user!=null){
            dto.setUserId(user.getId());
            logPublisher.sendLogMsg(dto);
            return true;
        }else{
            return false;
        }
    }
}




















