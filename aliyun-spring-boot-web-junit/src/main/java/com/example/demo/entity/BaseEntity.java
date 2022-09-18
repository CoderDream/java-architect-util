package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    protected String id;
    protected Date createDate;
    protected Date modifyDate;
}
