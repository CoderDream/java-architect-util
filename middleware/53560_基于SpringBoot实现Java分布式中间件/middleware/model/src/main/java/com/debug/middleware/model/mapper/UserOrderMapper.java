package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

public interface UserOrderMapper {
    //根据主键id删除记录
    int deleteByPrimaryKey(Integer id);
    //插入记录
    int insert(UserOrder record);
    //插入记录
    int insertSelective(UserOrder record);
    //根据主键id查询记录
    UserOrder selectByPrimaryKey(Integer id);
    //更新记录
    int updateByPrimaryKeySelective(UserOrder record);
    //更新记录
    int updateByPrimaryKey(UserOrder record);
    //根据下单记录Id和支付状态查询
    UserOrder selectByIdAndStatus(@Param("id") Integer id,@Param("status") Integer status);
}