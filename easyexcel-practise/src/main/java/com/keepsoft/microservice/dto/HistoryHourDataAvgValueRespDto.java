package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("发电流量小时")
@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryHourDataAvgValueRespDto implements Serializable {
    private static final long serialVersionUID = -66879204475172690L;

    /**
     * 数据时间
     */
    @ApiModelProperty("数据时间")
    private Date dataTime;

    /**
     * 流量平均值
     */
    @ApiModelProperty("流量平均值")
    private BigDecimal avgValue;

}
