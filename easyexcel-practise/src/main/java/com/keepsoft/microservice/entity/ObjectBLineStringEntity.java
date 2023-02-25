package com.keepsoft.microservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 地理数据-线
 *
 * @Author zj
 * @Date 2022/10/8 20:28
 */
@TableName(value = "object_b_linestring", autoResultMap = true)
@Data
public class ObjectBLineStringEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    private String attrItemFullCode;

    /**
     * 地理信息
     */
    @ApiModelProperty("地理信息")
    @TableField(value = "geometry", typeHandler = FastjsonTypeHandler.class)
    private JSONObject geometry;

    /**
     * 坐标信息
     */
    @ApiModelProperty("坐标信息")
    @TableField(value = "coordinates", typeHandler = FastjsonTypeHandler.class)
    private JSONObject coordinates;
    /**
     * 地理坐标
     */
    @ApiModelProperty("属性")
    @TableField(value = "properties", typeHandler = FastjsonTypeHandler.class)
    private JSONObject properties;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    @TableField(exist = false)
    private String objectName;

}
