package com.company.microserviceuser.dao;

import com.company.microserviceuser.vo.*;
import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.dos.*;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 用户信息增删改查.
 * @author xindaqi
 * @since 2020-11-08
 */
@Mapper
public interface UserDAO {

    /**
     * 查询用户详情
     * @param id 用户id
     * @return
     */
    UserDetailsVO queryUserDetails(Long id);

    /**
     * 登录时查询用户信息
     * @param params 用户名和用户密码
     * @return
     */
    LoginOutputDTO queryUserLoginDetails(LoginInputDTO params);

    /**
     * 新增用户
     * @param params 用户信息
     * @return
     */
    Integer addUser(UserDO params);

    /**
     * 物理删除用户
     * @param id 用户id
     * @return
     */
    Integer deleteUserPhysical(Long id);

    /**
     * 逻辑删除用户
     * @param params 用户参数
     * @return
     */
    Integer deleteUserLogical(UserDO params);

    /**
     * 修改用户
     * @param params 用户参数
     * @return
     */
    Integer editUser(UserDO params);

    /**
     * 分页查询用户信息
     * @param params 用户参数
     * @return
     */
    List<UserDetailsVO> queryUserByPage(UserDO params);

    /**
     * 批量参数查询用户信息
     * @param params
     * @return
     */
    List<UserDetailsVO> queryUserByIdList(QueryUserByIdListInputDTO params);

    /**
     * 查询用户信息和角色信息
     * @param id 用户id
     * @return 
     */
    UserAndRoleOutputDTO queryUserAndRole(Long id);

    /**
     * 查询数据库时间
     * @return
     */
    List<DateOutputDTO> queryDateInDatabase();
}

