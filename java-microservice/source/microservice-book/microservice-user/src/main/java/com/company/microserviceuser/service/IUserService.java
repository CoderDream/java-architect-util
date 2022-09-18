package com.company.microserviceuser.service;

import com.github.pagehelper.PageInfo;

import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.dos.*;
import com.company.microserviceuser.vo.*;

import java.util.List;

/**
 * 用户信息增删改查业务逻辑Interface
 * @author xindaqi
 * @since 2020-10-07
 */

public interface IUserService {

    /**
     * 查询用户信息
     * @param id 用户id
     * @return
     */
    UserDetailsVO queryUserDetails(Long id);

    /**
     * 用户登录
     * @param params
     * @return
     */
    LoginOutputDTO login(LoginInputDTO params);

    /**
     * 用户注册
     * @param params
     * @return
     */
    Integer registerUser(UserDO params);

    /**
     * 永久注销用户（物理删除）
     * @param id
     * @return
     */
    Integer deleteUserPhysical(Long id);

    /**
     * 注销用户（逻辑删除）
     * @param params 用户参数
     * @return
     */
    Integer deleteUserLogical(UserDO params);

    /**
     * 修改用户信息
     * @param params
     * @return
     */
    Integer editUser(UserDO params);

    /**
     * 分页查询用户信息
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserDetailsVO> queryUserByPage(UserDO params, Integer pageNum, Integer pageSize);

    /**
     * 批量查询用户信息
     * @param params
     * @return
     */
    PageInfo<UserDetailsVO> queryUserByIdList(QueryUserByIdListInputDTO params);

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    UserAndRoleVO queryUserAndRole(Long id);

    /**
     * 批量注册用户，用于测试事务回滚
     * @param userDOList 用户信息列表
     * @return
     */
    Integer registerUserBatch(List<UserDO> userDOList);

}