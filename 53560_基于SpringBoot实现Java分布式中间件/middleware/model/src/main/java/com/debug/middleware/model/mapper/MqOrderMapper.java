package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.MqOrder;

public interface MqOrderMapper {
    //根据主键id删除记录
    int deleteByPrimaryKey(Integer id);
    //插入记录
    int insert(MqOrder record);
    //插入记录
    int insertSelective(MqOrder record);
    //根据主键id查询记录
    MqOrder selectByPrimaryKey(Integer id);
    //更新记录
    int updateByPrimaryKeySelective(MqOrder record);
    //更新记录
    int updateByPrimaryKey(MqOrder record);
}