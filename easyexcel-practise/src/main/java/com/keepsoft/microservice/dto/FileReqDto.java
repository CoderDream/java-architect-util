package com.keepsoft.microservice.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileReqDto {

    @ApiModelProperty("条目编码")
    private String attrFullCode;
}
