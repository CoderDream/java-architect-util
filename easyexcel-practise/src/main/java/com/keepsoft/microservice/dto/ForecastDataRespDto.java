package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@ApiModel("预报数据请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class ForecastDataRespDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private String regId;

    /**
     * 依据时间（发出预报的时间）
     */
    @ApiModelProperty("依据时间")
    private Date forecastTime;

    /**
     * 预报时间（未来发生的时间）
     */
    @ApiModelProperty("预报时间")
    private Date futureTime;

    /**
     * 预报值
     */
    @ApiModelProperty("预报值")
    private Double forecastValue;
}
