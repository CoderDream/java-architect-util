package com.coderdream.easyexcelpractise.dto;

import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@TableName(value = "object_b_curve")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CurveDto {

    /**
     * curveid
     */
    private String curveid;

    /**
     * v0
     */
    private Double v0;

    /**
     * v1
     */
    private Double v1;

    /**
     * v2
     */
    private Double v2;

    /**
     * v3
     */
    private Double v3;

    /**
     * version
     */
    private String version;

}
