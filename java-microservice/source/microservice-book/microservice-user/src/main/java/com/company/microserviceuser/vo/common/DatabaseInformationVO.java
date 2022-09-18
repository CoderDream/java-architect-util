package com.company.microserviceuser.vo.common;

import lombok.Data;

/**
 * 数据库配置信息
 * @author xindaqi
 * @since 2020-11-21
 */
@Data
public class DatabaseInformationVO {

    private String jdbcUrl;

    private String dbType;

    private Long connectCount;

    private Integer initialSize;
    
}