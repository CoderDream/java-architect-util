package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel("预报数据响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class ForecastDetailRespDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 预报时间（未来发生的时间）
     */
    @ApiModelProperty("预报时间")
    private String futureTime;

    /**
     * 预报值
     */
    @ApiModelProperty("预报值")
    private Double forecastValue;
}
