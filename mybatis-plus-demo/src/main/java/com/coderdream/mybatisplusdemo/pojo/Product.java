package com.coderdream.mybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
//设置实体类对应的表名
@TableName("t_product")
public class Product {
    private Long id;
    private String name;
    private Integer price;

    @Version //表示乐观锁版本号字段
    private Integer version;//版本号加上 @Version 注解
}
