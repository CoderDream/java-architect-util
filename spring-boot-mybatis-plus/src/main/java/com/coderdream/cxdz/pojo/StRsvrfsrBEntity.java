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
 * 库（湖）站汛限水位
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_RSVRFSR_B")
@ApiModel(value="StRsvrfsrBEntity对象", description="库（湖）站汛限水位")
public class StRsvrfsrBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableField("STCD")
    private String stcd;

    @TableField("ACTYR")
    private BigDecimal actyr;

    @ApiModelProperty(value = "开始月日")
    @TableField("BGMD")
    private String bgmd;

    @ApiModelProperty(value = "结束月日")
    @TableField("EDMD")
    private String edmd;

    @ApiModelProperty(value = "汛限水位")
    @TableField("FSLTDZ")
    private BigDecimal fsltdz;

    @ApiModelProperty(value = "汛限库容")
    @TableField("FSLTDW")
    private BigDecimal fsltdw;

    @TableField("FSLTDZT")
    private BigDecimal fsltdzt;

    @ApiModelProperty(value = "汛期类别")
    @TableField("FSTP")
    private String fstp;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private Date moditime;


}
