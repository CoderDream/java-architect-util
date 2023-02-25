package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@TableName(value = "t_history_hour_data")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AttrInfoEntity {
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
}
