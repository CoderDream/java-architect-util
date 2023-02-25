package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@ApiModel("预报数据响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class ForecastInfoRespDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;


    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private String dataType;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;

    /**
     * 依据时间（发出预报的时间）
     */
    @ApiModelProperty("依据时间")
    private String forecastTime;

    /**
     * 预报详情
     */
    @ApiModelProperty("预报详情")
    private List<ForecastDetailRespDto> forecastDetails;
}
