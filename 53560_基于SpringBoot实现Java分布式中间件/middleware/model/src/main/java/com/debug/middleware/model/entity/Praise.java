package com.debug.middleware.model.entity;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 点赞信息实体
 */
@Data
@ToString
public class Praise {
    private Integer id;         //主键id
    private Integer blogId;     //博客id
    private Integer userId;     //点赞人id
    private Date praiseTime;    //点赞时间
    private Integer status;     //点赞的状态
    private Integer isActive;   //是否有效
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
}