package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaiKeSearchDto {
    /**
     * 查询关键词
     */
    @ApiModelProperty("查询关键词")
    private String searchKey;

    /**
     * 页面大小
     */
    @ApiModelProperty("页面大小")
    private Integer pageSize;

    /**
     * 页面数量
     */
    @ApiModelProperty("页面数量")
    private Integer pageNum;
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private String id;

    /**
     * 热点词汇查询次数
     */
    @ApiModelProperty("热点词汇查询次数")
    private Integer hotSearchWordCount;

    /**
     * 标签名称
     */
    @ApiModelProperty("标签名称")
    private String labelName;

    /**
     * neo4j数据库ID
     */
    @ApiModelProperty("neo4j数据库ID")
    private Long neo4jId;

    /**
     * 子类型名称
     */
    @ApiModelProperty("子类型名称")
    private String subTypeName;

    /**
     * 地址名称
     */
    @ApiModelProperty("地址名称")
    private String addressName;

    /**
     * 区域
     */
    @ApiModelProperty("区域")
    private String region;

    /**
    * 编码
     */
    @ApiModelProperty("编码")
    private String code;

    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private String level;
}

