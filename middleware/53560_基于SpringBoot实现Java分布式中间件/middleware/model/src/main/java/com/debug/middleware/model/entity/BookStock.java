package com.debug.middleware.model.entity;

import lombok.Data;
import lombok.ToString;

//书籍库存实体
@Data
@ToString
public class BookStock {
    private Integer id;   //主键Id
    private String bookNo;//书籍编号
    private Integer stock;//存库
    private Byte isActive;//是否上架
}