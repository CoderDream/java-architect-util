package com.company.microserviceuser.dos;

import lombok.Data;

/**
 * 角色表
 * @author xindaqi
 * @since 2020-11-21
 */

@Data
public class RoleDO {

    private Long id;

    private String roleId;

    private Integer delete;

    private String createdTime;

    private String updatedTime;

}