package com.company.microserviceuser.dos;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * User table.
 * @author xindaqi
 * @since 2020-11-08
 */
@Data
public class UserDO {

    private Long id;

    private String userId;

    private String username;

    private String sex;

    private String address;

    private String phoneNumber;

    private String password;

    private Integer delete;

    private String createdTime;

    private String updatedTime;

   
    
}