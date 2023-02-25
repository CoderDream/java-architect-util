package com.keepsoft.microservice.entity;

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
@TableName(value = "t_forecast_hour_data", autoResultMap = true)
@Data
public class ForecastHourDataEntity implements Serializable {
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
     * 步长
     */
    @ApiModelProperty("步长")
    @TableField(value = "step_length")
    private Integer stepLength;

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
