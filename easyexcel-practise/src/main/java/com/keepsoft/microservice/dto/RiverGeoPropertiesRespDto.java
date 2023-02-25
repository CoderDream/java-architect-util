package com.keepsoft.microservice.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName(value = "t_river_geo")
@ApiModel("河流地理信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class RiverGeoPropertiesRespDto implements Serializable {

    /**
     * NAME
     */
    @ApiModelProperty("NAME")
    private String NAME;

    /**
     * OBJECTID
     */
    @ApiModelProperty("OBJECTID")
    private Integer OBJECTID;

    /**
     * FNODE_
     */
    @ApiModelProperty("FNODE")
    private Integer FNODE;

    /**
     * TNODE_
     */
    @ApiModelProperty("TNODE")
    private Integer TNODE;

    /**
     * LPOLY_
     */
    @ApiModelProperty("LPOLY")
    private Integer LPOLY;

    /**
     * RPOLY_
     */
    @ApiModelProperty("RPOLY")
    private Integer RPOLY;

    /**
     * LENGTH
     */
    @ApiModelProperty("LENGTH")
    private BigDecimal LENGTH;

    /**
     * HYDR_
     */
    @ApiModelProperty("HYDR")
    private Integer HYDR;

    /**
     * HYDR_ID
     */
    @ApiModelProperty("HYDR_ID")
    private Integer HYDR_ID;

    /**
     * CODE
     */
    @ApiModelProperty("CODE")
    private Integer CODE;

    /**
     * GB
     */
    @ApiModelProperty("GB")
    private Integer GB;

    /**
     * HYDC
     */
    @ApiModelProperty("HYDC")
    private String HYDC;

    /**
     * TN
     */
    @ApiModelProperty("TN")
    private Integer TN;

    /**
     * MAPTN
     */
    @ApiModelProperty("MAPTN")
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

}
