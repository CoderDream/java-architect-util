package com.coderdream.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.mybatisplusdemo.entity.User;
import com.coderdream.mybatisplusdemo.entity.User2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface User2Mapper extends BaseMapper<User2> {
    /**
     * 根据id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);

    /**
     * 根据年龄查询用户信息，分页显示
     * @param page MyBatis-Plus所提供的分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一个参数位置
     * @param age 年龄
     * @return
     */
    Page<User2> selectPageVo(@Param("page")Page<User2> page, @Param("age") Integer age);
}
