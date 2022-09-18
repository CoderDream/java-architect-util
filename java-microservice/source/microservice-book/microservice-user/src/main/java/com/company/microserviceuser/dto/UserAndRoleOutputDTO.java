package com.company.microserviceuser.dto;

import lombok.Data;
import java.util.List;

/**
 * 用户及角色
 * @author xindaqi
 * @since 2020-11-21
 */

@Data
public class UserAndRoleOutputDTO {

    private Integer id;

    private String username;

    private String sex;

    private String address;

    private List<RoleOutputDTO> roleList;
}