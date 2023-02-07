package com.coderdream.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 1064 - You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version
 * for the right syntax to use near 'LONG NOT NULL AUTO_INCREMENT PRIMARY KEY' at line 2
 */
@Data
@ToString
public class User {
    // 常见的主键生成策略(UUID/MySQL自增长/雪花算法/redis/zookeeper...)
    @TableId(type = IdType.AUTO) // 手工创建自增长
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 配置乐观锁插件
    @Version // 乐观锁注解
    private Integer version;

//    // 开始时间
//    private Date gmtCreate;
//    // 结束时间
//    private Date gmrModified;

    // 开始时间
// 插入填充字段
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    // 结束时间
// 更新填充字段
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    // 逻辑删除
    @TableLogic // 逻辑删除注解
    private Integer deleted;
}
