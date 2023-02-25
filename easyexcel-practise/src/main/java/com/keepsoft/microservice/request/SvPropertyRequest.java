package com.keepsoft.microservice.request;

import lombok.Data;

/**
 * 数据服务的属性信息
 */
@Data
public class SvPropertyRequest {

    //名称
    private String name;

    //描述
    private String description;

    //是否必须
    private Boolean require;

    //类型
    private String type;

    //默认值
    private String dfVal;
}
