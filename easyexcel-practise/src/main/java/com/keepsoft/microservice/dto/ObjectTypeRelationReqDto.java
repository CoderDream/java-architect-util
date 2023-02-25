package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("属性与对象类型关联关系请求数据传输对象")
public class ObjectTypeRelationReqDto implements Serializable {
    private static final long serialVersionUID = -66879321475172690L;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     *属性编码列表
     */
    @ApiModelProperty("属性编码列表")
    private List<String> attrCodeList;

}
