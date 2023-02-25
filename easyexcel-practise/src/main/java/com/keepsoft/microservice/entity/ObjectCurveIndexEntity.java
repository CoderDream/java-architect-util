package com.keepsoft.microservice.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName(value = "object_curve_index", autoResultMap = true)
@Data
public class ObjectCurveIndexEntity {

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("编号")
    @TableField(value = "OBJClass")
    private String objclass;

    @ApiModelProperty("编号")
    @TableField(value = "OBJNAME")
    private String objname;

    @ApiModelProperty("编号")
    @TableField(value = "OBJATTRClassID")
    private String objattrclassid;

    @ApiModelProperty("编号")
    @TableField(value = "OBJATTRClassSubD")
    private String objattrclasssubd;

    @ApiModelProperty("编号")
    @TableField(value = "CurveName")
    private String curvename;

    @ApiModelProperty("编号")
    @TableField(value = "NID")
    private String nid;

    @ApiModelProperty("编号")
    @TableField(value = "PID")
    private String pid;

    @ApiModelProperty("编号")
    @TableField(value = "DIM")
    private String dim;

    @ApiModelProperty("编号")
    @TableField(value = "DIMNAME")
    private String dimname;

}
