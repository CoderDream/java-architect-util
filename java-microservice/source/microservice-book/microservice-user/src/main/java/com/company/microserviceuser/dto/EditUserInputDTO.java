package com.company.microserviceuser.dto;

import lombok.Data;

/**
 * Edit user.
 * @author xindaqi
 * @since 2020-11-09
 */
@Data
public class EditUserInputDTO {

    private Long id;

    private String username;

    private String password;

    private String sex;

    private String address;

    private String phoneNumber;

}