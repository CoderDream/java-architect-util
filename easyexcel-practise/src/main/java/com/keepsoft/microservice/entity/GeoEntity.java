package com.keepsoft.microservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.keepsoft.microservice.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("河流地理信息")
@Data
@TableName(value = "t_area_geo", autoResultMap = true)
public class GeoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * feature_type
     */
    @ApiModelProperty("feature_type")
    @TableField(value = "feature_type")
    private String featureType;


    /**
     * property_id
     */
    @ApiModelProperty("property_id")
    @TableField(value = "property_id")
    private BigDecimal propertyId;

    /**
     * name
     */
    @ApiModelProperty("name")
    @TableField(value = "name")
    private String name;

    /**
     * 地理坐标
     */
    @ApiModelProperty("地理坐标")
    @TableField(value = "geometry_info", typeHandler = FastjsonTypeHandler.class)
//    @TableField(value = "geometry_info", javaType =true, jdbcType = JdbcType.LONGVARCHAR, typeHandler = JsonObjectTypeHandler.class)
    private JSONObject geometryInfo;

}
