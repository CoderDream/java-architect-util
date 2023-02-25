package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("流域地理信息属性部分")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaGeoPropertiesRespDto implements Serializable {

    /**
     * id
     */
    @ApiModelProperty("id")
    private BigDecimal id;

    /**
     * NAME
     */
    @ApiModelProperty("NAME")
    private String NAME;

}
