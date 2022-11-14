package com.coderdream.easyexcelpractise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BasicAttrInfoDto {
    private static final long serialVersionUID = -66879444475372690L;

    /**
     * 属性or类型
     */
    private String attrCategory;

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
     * 类型
     */
    private String dataType;

    /**
     * 是否公共
     */
    private String commonFlag;

    /**
     * 描述
     */
    private String remark;

    /**
     * 描述
     */
    private List<BasicAttrInfoDto> attrInfoDtoList;

}