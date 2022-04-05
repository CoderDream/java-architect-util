package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserAccountRecord;

public interface UserAccountRecordMapper {
    //插入记录
    int insert(UserAccountRecord record);
    //根据主键id查询
    UserAccountRecord selectByPrimaryKey(Integer id);
}