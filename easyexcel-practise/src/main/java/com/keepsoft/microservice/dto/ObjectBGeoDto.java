package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ObjectBGeoDto {


    @ApiModelProperty("attr_item_full_code")
    private String attrItemFullCode;

    @ApiModelProperty("value")
    private String value;

    @ApiModelProperty("object_full_code")
    private String objectFullCode;
}
