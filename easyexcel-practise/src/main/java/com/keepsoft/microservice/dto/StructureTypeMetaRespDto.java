package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("类型元数据信息")
public class StructureTypeMetaRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 结构名称
     */
    @ApiModelProperty("结构名称")
    private String structureTypeName;

    /**
     * 结构标识
     */
    @ApiModelProperty("结构标识")
    private String structureTypeLabel;

    /**
     * 结构编码
     */
    @ApiModelProperty("结构编码")
    private String structureTypeCode;

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
     * 结构描述
     */
    @ApiModelProperty("结构描述")
    private String remark;
}
