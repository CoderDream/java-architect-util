package com.coderdream.mybatisplusdemo.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.coderdream.mybatisplusdemo.data.ExtraNode;
import com.coderdream.mybatisplusdemo.handler.ObjectAndJsonHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//@TableName(value = "t_river_geo")
//@ApiModel("河流地理信息")
//@Data
//@EqualsAndHashCode(callSuper = false)
//public class RiverGeoEntity extends BaseEntity {
//
//    private static final long serialVersionUID = -66879404475172690L;
//
//    /**
//     * 主键id
//     */
//    @ApiModelProperty("主键id")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
@Data
@TableName(value = "t_river_geo", autoResultMap = true)
public class RiverGeoEntity extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * feature_type
     */
    @ApiModelProperty("feature_type")
    @TableField(value = "feature_type")
    private String featureType;

    /**
     * NAME
     */
    @ApiModelProperty("NAME")
    @TableField(value = "NAME")
    private String NAME;

    /**
     * OBJECTID
     */
    @ApiModelProperty("OBJECTID")
    @TableField(value = "OBJECTID")
    private Integer OBJECTID;

    /**
     * FNODE_
     */
    @ApiModelProperty("FNODE")
    @TableField(value = "FNODE")
    private Integer FNODE;

    /**
     * TNODE_
     */
    @ApiModelProperty("TNODE")
    @TableField(value = "TNODE")
    private Integer TNODE;

    /**
     * LPOLY_
     */
    @ApiModelProperty("LPOLY")
    @TableField(value = "LPOLY")
    private Integer LPOLY;

    /**
     * RPOLY_
     */
    @ApiModelProperty("RPOLY")
    @TableField(value = "RPOLY")
    private Integer RPOLY;

    /**
     * LENGTH
     */
    @ApiModelProperty("LENGTH")
    @TableField(value = "LENGTH")
    private BigDecimal LENGTH;

    /**
     * HYDR_
     */
    @ApiModelProperty("HYDR")
    @TableField(value = "HYDR")
    private Integer HYDR;

    /**
     * HYDR_ID
     */
    @ApiModelProperty("HYDR_ID")
    @TableField(value = "HYDR_ID")
    private Integer hydrId;


    /**
     * CODE
     */
    @ApiModelProperty("CODE")
    @TableField(value = "CODE")
    private Integer CODE;

    /**
     * GB
     */
    @ApiModelProperty("GB")
    @TableField(value = "GB")
    private Integer GB;

    /**
     * HYDC
     */
    @ApiModelProperty("HYDC")
    @TableField(value = "HYDC")
    private String HYDC;

    /**
     * TN
     */
    @ApiModelProperty("TN")
    @TableField(value = "TN")
    private Integer TN;

    /**
     * MAPTN
     */
    @ApiModelProperty("MAPTN")
    @TableField(value = "MAPTN")
    private String MAPTN;

    /**
     * SHAPE_LENG
     */
    @ApiModelProperty("SHAPE_LENG")
    @TableField(value = "SHAPE_LENG")
    private BigDecimal shapeLeng;

    /**
     * LEVEL
     */
    @ApiModelProperty("LEVEL")
    @TableField(value = "LEVEL")
    private Integer LEVEL;

    /**
     * 地理坐标
     */
    @ApiModelProperty("地理坐标")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject geometryInfo;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraNode extraObject;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject extraJson;

    @TableField(typeHandler = ObjectAndJsonHandler.class)
    private List<ExtraNode> extraList;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraNode[] extraArray;
}
