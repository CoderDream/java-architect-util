package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel("历史数据请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryHourDataReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;

    /**
     * 周期
     */
    @ApiModelProperty("周期")
    private String period;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private String endTime;

}
