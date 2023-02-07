package com.coderdream.cxdz.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 典型洪水水位流量关系
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_ZQRL_B")
@ApiModel(value="StZqrlBEntity对象", description="典型洪水水位流量关系")
public class StZqrlBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableField("STCD")
    private String stcd;

    @ApiModelProperty(value = "启用时间")
    @TableField("BGTM")
    private Date bgtm;

    @ApiModelProperty(value = "点序号")
    @TableField("PTNO")
    private BigDecimal ptno;

    @ApiModelProperty(value = "曲线名称")
    @TableField("LNNM")
    private String lnnm;

    @ApiModelProperty(value = "水位")
    @TableField("Z")
    private BigDecimal z;

    @ApiModelProperty(value = "流量")
    @TableField("Q")
    private BigDecimal q;

    @TableField("NOTE")
    private String note;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private Date moditime;

    @ApiModelProperty(value = "备注")
    @TableField("COMMENTS")
    private String comments;


}
