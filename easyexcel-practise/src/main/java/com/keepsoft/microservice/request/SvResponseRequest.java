package com.keepsoft.microservice.request;

import lombok.Data;

/**
 * 服务相应参数
 */
@Data
public class SvResponseRequest {

    //名称
    private String name;

    //参数位置
    private String in;

    //参数属性
    private String property;


    //参数类型
    private String type;

    //描述信息
    private String describe;
}
