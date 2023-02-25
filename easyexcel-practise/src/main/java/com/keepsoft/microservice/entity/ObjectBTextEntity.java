package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zj
 * @Date 2022/10/8 20:28
 */
@TableName(value = "object_b_text",autoResultMap=true)
@Data
public class ObjectBTextEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    private String attrItemFullCode;

    private String value;

}
