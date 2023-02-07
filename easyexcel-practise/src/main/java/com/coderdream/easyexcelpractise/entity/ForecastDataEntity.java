package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 预报数据
 * @Date 2022/10/8 20:28
 */
@TableName(value = "forecast_data",autoResultMap=true)
@Data
public class ForecastDataEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * regId
     */
    @ApiModelProperty("regId")
    @TableField(value = "reg_id")
    private String regId;

    /**
     * 依据时间（发出预报的时间）
     */
    @ApiModelProperty("依据时间")
    @TableField(value = "forecast_time")
    private Date forecastTime;

    /**
     * 预报时间（未来发生的时间）
     */
    @ApiModelProperty("预报时间")
    @TableField(value = "future_time")
    private Date futureTime;

    /**
     * 预报值
     */
    @ApiModelProperty("预报值")
    @TableField(value = "forecast_value")
    private Double forecastValue;

}
