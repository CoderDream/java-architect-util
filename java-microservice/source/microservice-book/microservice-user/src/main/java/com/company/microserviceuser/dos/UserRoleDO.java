package com.company.microserviceuser.dos;

import lombok.Data;

/**
 * 用户和角色中间表
 * @author xindaqi
 * @since 2020-11-21
 */
@Data
public class UserRoleDO {

    private Long id;

    private String userId;

    private String roleId;

    private Integer delete;

    private String createdTime;

    private String updatedTime;
    
}