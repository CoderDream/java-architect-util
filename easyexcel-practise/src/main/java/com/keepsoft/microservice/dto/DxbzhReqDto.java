package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@ApiModel(value = "基础属性请求实体")
public class DxbzhReqDto implements Serializable {
    private static final long serialVersionUID=-66879324475172690L;


    @ApiModelProperty("类别")
    private String type;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
