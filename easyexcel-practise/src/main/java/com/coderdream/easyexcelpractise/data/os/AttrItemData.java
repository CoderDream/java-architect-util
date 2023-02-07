package com.coderdream.easyexcelpractise.data.os;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 属性条目
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AttrItemData {
    /**
     * 属性条目（名称）标识		结构类型	属性类型	属性	算力层级	数据来源	备注
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
     * 对象
     */
    private String objectName;

    /**
     * 结构类型
     */
    private String structureTypeName;

    /**
     * 属性类型
     */
    private String attrTypeName;

    /**
     * 属性
     */
    private String attrName;

    /**
     * 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
     */
    @ApiModelProperty("属性的算力级别")
    private String hashRateLevel;

    /**
     * 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源
     */
    @ApiModelProperty("数据来源")
    private String dataSource;

    /**
     * 描述
     */
    private String remark;

}
