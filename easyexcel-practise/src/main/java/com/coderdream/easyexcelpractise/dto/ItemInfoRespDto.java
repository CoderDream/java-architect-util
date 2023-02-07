package com.coderdream.easyexcelpractise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel("对象地理信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class ItemInfoRespDto implements Serializable {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 精度
     */
    @ApiModelProperty("精度")
    private String longitude;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String type;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 空间级别
     */
    @ApiModelProperty("空间级别")
    private String spaceLevel;
}
