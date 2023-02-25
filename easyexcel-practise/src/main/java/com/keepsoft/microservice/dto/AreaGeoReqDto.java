package com.keepsoft.microservice.dto;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@ApiModel("河流地理信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class AreaGeoReqDto {
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
    private String feature_type;

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
    private Integer HYDR_ID;


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
    private BigDecimal SHAPE_LENG;

    /**
     * LEVEL
     */
    @ApiModelProperty("LEVEL")
    private Integer LEVEL;

    /**
     * 地理坐标
     */
    @ApiModelProperty("地理坐标")
    private JSONObject geometry;


}
