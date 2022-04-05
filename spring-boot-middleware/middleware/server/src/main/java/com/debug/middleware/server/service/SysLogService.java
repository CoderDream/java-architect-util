package com.debug.middleware.server.service;/**
 * Created by Administrator on 2019/4/7.
 */

import com.debug.middleware.model.entity.SysLog;
import com.debug.middleware.model.mapper.SysLogMapper;
import com.debug.middleware.server.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 系统日志服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:07
 **/
@Service
@EnableAsync
public class SysLogService {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(SysLogService.class);
    //定义系统日志操作接口Mapper
    @Autowired
    private SysLogMapper sysLogMapper;
    //定义Json序列化和反序列化组件
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 记录用户登录成功的信息入数据库
     * @param dto
     */
    @Async
    public void recordLog(UserLoginDto dto){
        try {
            SysLog entity=new SysLog();
            entity.setUserId(dto.getUserId());
            entity.setModule("用户登录模块");
            entity.setData(objectMapper.writeValueAsString(dto));
            entity.setMemo("用户登录成功记录相关登录信息");
            entity.setCreateTime(new Date());

            //插入数据库
            sysLogMapper.insertSelective(entity);
        }catch (Exception e){
            log.error("系统日志服务-记录用户登录成功的信息入数据库-发生异常：{} ",dto,e.fillInStackTrace());
        }
    }
}

































