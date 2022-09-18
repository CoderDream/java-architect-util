package com.example.demo.entity;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String mobile;
}
