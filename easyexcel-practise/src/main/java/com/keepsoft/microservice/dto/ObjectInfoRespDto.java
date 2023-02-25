package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("对象信息响应DTO")
public class ObjectInfoRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectTypeFullCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectFullCode;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    private String objectName;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 空间维度
     */
    @ApiModelProperty("空间维度")
    private String spaceLevel;

    /**
     * 地理信息
     */
    @ApiModelProperty("地理信息")
    private String geom;

    /**
     * 测站编码
     */
    @ApiModelProperty("测站编码")
    private String stcd;
}
