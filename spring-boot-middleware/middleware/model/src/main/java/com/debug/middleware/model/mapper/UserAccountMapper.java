package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    //根据主键id查询
    UserAccount selectByPrimaryKey(Integer id);
    //根据用户账户Id查询
    UserAccount selectByUserId(@Param("userId") Integer userId);

    //更新账户金额
    int updateAmount(@Param("money") Double money,@Param("id") Integer id);
    //根据主键id跟version进行更新
    int updateByPKVersion(@Param("money") Double money,@Param("id") Integer id,@Param("version") Integer version);

    //根据用户id查询记录-for update方式
    UserAccount selectByUserIdLock(@Param("userId") Integer userId);
    //更新账户金额-悲观锁的方式
    int updateAmountLock(@Param("money") Double money,@Param("id") Integer id);
}