package com.coderdream.easyexcelpractise.data.os;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 对象实例
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ObjectInstanceData {
    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 标识
     */
    private String label;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 空间维度 二维或三维
     */
    @ApiModelProperty("空间维度")
    private String spaceLevel;

    /**
     * 描述
     */
    private String remark;

    /**
     * 站号
     */
    private String stationNo;
}
