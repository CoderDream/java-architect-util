package com.coderdream.easyexcelpractise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel("类型元数据信息响应数据传输对象")
public class ObjectTypeMetaRespDto implements Serializable {
    private static final long serialVersionUID = -66879323475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 类型名称
     */
    private String objectTypeName;

    /**
     * 类型标识
     */
    @ApiModelProperty("类型标识")
    private String objectTypeLabel;

    /**
     * 类型编码
     */
    @ApiModelProperty("类型编码")
    private String objectTypeCode;

    /**
     * 类型全码
     */
    @ApiModelProperty("类型全码")
    private String objectTypeFullCode;


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
     * 类型描述
     */
    @ApiModelProperty("类型描述")
    private String remark;
}
