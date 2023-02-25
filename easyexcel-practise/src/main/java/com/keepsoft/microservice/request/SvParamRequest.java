package com.keepsoft.microservice.request;

import lombok.Data;

/**
 * 服务参数信息
 */
@Data
public class SvParamRequest {

    //名称
    private String name;

    //参数位置
    private String in;

    //参数属性
    private String property;

    //是否必须
    private Integer required;

    //参数类型
    private String type;

    //描述信息
    private String describe;
}
