package com.keepsoft.microservice.dto.os;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("对象元数据信息请求数据传输对象")
public class ObjectMetaRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    private String objectName;

    /**
     * 对象标识
     */
    @ApiModelProperty("对象标识")
    private String objectLabel;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    private String objectCode;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    private String objectFullCode;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 对象描述
     */
    @ApiModelProperty("对象描述")
    private String remark;

    /**
     * 对象所属类型编码
     */
    @ApiModelProperty("对象所属类型编码")
    private String objectTypeCode;

    /**
     * 对象所属类型全码
     */
    @ApiModelProperty("对象所属类型全码")
    private String objectTypeFullCode;

    /**
     * 对象所属类型名称
     */
    @ApiModelProperty("对象所属类型名称")
    private String objectTypeName;

    /**
     * 对象的空间维度等级，分为L1、L2、L3
     */
    @ApiModelProperty("对象的空间维度等级")
    private String spaceLevel;
}
