package com.company.microserviceuser.service.impl;

import com.github.pagehelper.page.PageMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;

import java.util.stream.Collectors;

import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.service.*;
import com.company.microserviceuser.dao.*;
import com.company.microserviceuser.dos.*;
import com.company.microserviceuser.vo.*;

import java.util.List;

/**
 * 用户信息接口实现
 * @author xindaqi
 * @since 2020-10-07
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetailsVO queryUserDetails(Long id) {
        return userDao.queryUserDetails(id);
    }

    @Override
    public LoginOutputDTO login(LoginInputDTO params){
        return userDao.queryUserLoginDetails(params);
    }

    @Override
    public Integer registerUser(UserDO params) {
        return userDao.addUser(params);
    }

    @Override
    public Integer deleteUserPhysical(Long id) {
        return userDao.deleteUserPhysical(id);
    }

    @Override
    public Integer deleteUserLogical(UserDO params) {
        return userDao.deleteUserLogical(params);
    }

    @Override
    public Integer editUser(UserDO params) {
        return userDao.editUser(params);
    }

    @Override
    public PageInfo<UserDetailsVO> queryUserByPage(UserDO params, Integer pageNum, Integer pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<UserDetailsVO> userList = userDao.queryUserByPage(params);
        return new PageInfo<>(userList);
    }

    @Override 
    public PageInfo<UserDetailsVO> queryUserByIdList(QueryUserByIdListInputDTO params) {
        Integer pageNum = params.getPageNum();
        Integer pageSize = params.getPageSize();
        PageMethod.startPage(pageNum, pageSize);
        List<UserDetailsVO> userList = userDao.queryUserByIdList(params);

        return new PageInfo<>(userList);
    }

    @Override 
    public UserAndRoleVO queryUserAndRole(Long id) {
        UserAndRoleOutputDTO userAndRoleOutputDTO = userDao.queryUserAndRole(id);
        List<String> roleList = userAndRoleOutputDTO.getRoleList().stream().map(RoleOutputDTO::getRoleId).collect(Collectors.toList());
        UserAndRoleVO userAndRoleVO = new UserAndRoleVO();
        BeanUtils.copyProperties(userAndRoleOutputDTO, userAndRoleVO);
        userAndRoleVO.setRoleList(roleList);
        return userAndRoleVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer registerUserBatch(List<UserDO> userDOList) {
        
        userDOList.stream().forEach( s -> {
            if(null == s.getUserId() || s.getUserId().isEmpty()) {
                throw new IllegalArgumentException("用户id不能为空");
            }
            userDao.addUser(s);
        });
        return 1;
    }
}