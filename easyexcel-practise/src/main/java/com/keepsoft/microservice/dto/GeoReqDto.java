package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@ApiModel("地理信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class GeoReqDto {
    private static final long serialVersionUID = -66879404475172690L;


    @ApiParam("对象类型全码")
    private String objectTypeFullCode;
    @ApiParam("名称键")
    private String[] nameArray;
    @ApiParam("名称键")
    private List<String> nameList;
    @ApiParam("ID键")
    private String idKey;
    @ApiParam("来源系统")
    private String sourceSystem;
    @ApiParam("用户名")
    private String userName;
    @ApiParam("级别")
    private String level;
    @ApiParam("备注")
    private String remark;
    @ApiParam("字符集")
    private String charset;


}
