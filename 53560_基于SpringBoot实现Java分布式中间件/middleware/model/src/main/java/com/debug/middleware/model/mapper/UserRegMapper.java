package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserReg;
        import org.apache.ibatis.annotations.Param;

/**
 * 用户注册实体Mapper操作接口
 */
public interface UserRegMapper {
    //插入用户注册信息
    int insertSelective(UserReg record);
    //根据用户名查询用户实体
    UserReg selectByUserName(@Param("userName") String userName);
}