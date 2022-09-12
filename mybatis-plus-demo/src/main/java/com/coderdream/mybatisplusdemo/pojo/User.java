package com.coderdream.mybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.coderdream.mybatisplusdemo.enums.SexEnum;
import lombok.*;

//@Data
//@NoArgsConstructor  //无参构造
//@AllArgsConstructor //有参构造
//@Getter //get方法
//@Setter //set方法
//@EqualsAndHashCode //equal和hashCode方法
@Data
//设置实体类对应的表名
@TableName("user")
public class User {

    //设置主键：将这个属性所对应的字段指定为主键
    //@TableId 注解的value属性用于指定主键的字段
    //@TableId 注解的type属性设置主键生成策略
    @TableId(value = "id",type = IdType.ASSIGN_ID) //这里的value是数据库的字段名
    private Long id; //数据库表id是bigint

    //指定属性所对应的字段名
    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;

    //逻辑删除字段
    @TableLogic
    private Integer isDeleted;
}
