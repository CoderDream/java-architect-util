package com.keepsoft.microservice.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 地理数据-点
 * @Date 2022/12/9 20:28
 */
@TableName(value = "object_b_point", autoResultMap = true)
@Data
public class ObjectBPointEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @TableField(value = "attr_item_full_code")
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
