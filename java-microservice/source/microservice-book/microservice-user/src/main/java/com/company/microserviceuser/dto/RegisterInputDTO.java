package com.company.microserviceuser.dto;

import lombok.Data;

/**
 * @author xindaqi
 * @since 2020-10-11
 */
@Data
public class RegisterInputDTO {

    private String username;

    private String password;

    private String sex;

    private String address;

    private String phoneNumber;
    
}