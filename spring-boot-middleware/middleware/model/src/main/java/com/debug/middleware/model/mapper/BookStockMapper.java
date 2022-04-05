package com.debug.middleware.model.mapper;

import com.debug.middleware.model.entity.BookStock;
import org.apache.ibatis.annotations.Param;
//书籍库存实体操作接口Mapper
public interface BookStockMapper {
    //根据书籍编号查询
    BookStock selectByBookNo(@Param("bookNo") String bookNo);
    //更新书籍库存-不加锁
    int updateStock(@Param("bookNo") String bookNo);
    //更新书籍库存-加锁
    int updateStockWithLock(@Param("bookNo") String bookNo);
}