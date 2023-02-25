package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("预报数据请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class ForecastInfoReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @NotNull(message = "属性条目全码不能为空")
    private String attrItemFullCode;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @NotNull(message = "开始时间不能为空")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @NotNull(message = "结束时间不能为空")
    private String endTime;
}
