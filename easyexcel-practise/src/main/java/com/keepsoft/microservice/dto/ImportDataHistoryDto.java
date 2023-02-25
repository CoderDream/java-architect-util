package com.keepsoft.microservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@ApiModel("导入记录dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class ImportDataHistoryDto extends BaseDto {


    @ApiModelProperty("type")
    private String type;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("status")
    private String status;

    @ApiModelProperty("errorFlag")
    private String errorFlag;

//
//    @ApiModelProperty("createTime")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    private Date createTime;
//


    @ApiModelProperty("completeTime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date completeTime;

    @ApiModelProperty("createUser")
    private String createUser;

    @ApiModelProperty("erroId")
    private String erroId;

    @ApiModelProperty("remark")
    private String remark;

//    @ApiModelProperty("startTime")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    @TableField(exist=false)
//    private Date startTime;
//
//    @ApiModelProperty("endTime")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    @TableField(exist=false)
//    private Date endTime;
}
