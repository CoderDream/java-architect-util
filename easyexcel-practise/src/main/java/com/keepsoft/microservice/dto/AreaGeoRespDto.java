package com.keepsoft.microservice.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName(value = "t_area_geo")
@ApiModel("河流地理信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaGeoRespDto {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * feature_type
     */
    @ApiModelProperty("type")
    private String type;

    /**
     * properties
     */
    @ApiModelProperty("properties")
    private AreaGeoPropertiesRespDto properties;

    /**
     * 地理坐标
     */
    @ApiModelProperty("地理坐标")
    private JSONObject geometry;

}
