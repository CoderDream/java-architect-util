package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("Base数据传输对象")
public class BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324375172190L;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Integer current;

    /**
     * 总记录条数
     */
    @ApiModelProperty("总记录条数")
    private Integer total;

    /**
     * 页面大小
     */
    @ApiModelProperty("页面大小")
    private Integer size = 10000000;
}
