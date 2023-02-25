package com.keepsoft.microservice.request;

import lombok.Data;

import java.util.List;

/**
 * 调用数据服务的实体类
 */
@Data
public class SvRequest {

    //服务id
    private Long serviceId;

    //服务名称
    private String name;

    //服务地址
    private String url;

    //接口类型 get post
    private String method;

    //请求类型
    private String consume;

    //返回类型
    private String produce;

    //是否运行调试
    private Integer debugEnable;

    //发布用户
    private String userId;

    //项目
    private String project;

    //接口名称
    private String engName;

    //应用id
    private Long appId;

    //参数信息
    List<SvParamRequest> params;

    //相应信息
    List<SvResponseRequest> responses;

    private Long id;
}
