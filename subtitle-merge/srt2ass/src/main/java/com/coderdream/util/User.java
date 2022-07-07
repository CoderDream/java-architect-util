package com.coderdream.util;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String name;
    private String sex;
    private Integer age;
    private Date birthday;

}
