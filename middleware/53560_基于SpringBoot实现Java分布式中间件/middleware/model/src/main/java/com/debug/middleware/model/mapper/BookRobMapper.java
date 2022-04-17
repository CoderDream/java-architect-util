package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.BookRob;
import org.apache.ibatis.annotations.Param;
//书籍抢购成功的记录实体Mapper操作接口
public interface BookRobMapper {
    //插入抢购成功的记录信息
    int insertSelective(BookRob record);
    //统计每个用户每本书的抢购数量
    //用于判断用户是否抢购过该书籍
    int countByBookNoUserId(@Param("userId") Integer userId,@Param("bookNo") String bookNo);
}