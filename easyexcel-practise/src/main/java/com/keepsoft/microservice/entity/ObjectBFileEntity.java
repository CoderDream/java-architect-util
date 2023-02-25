package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;

/**
 * @Author zj
 * @Date 2022/10/8 20:28
 */
@TableName(value = "object_b_File", autoResultMap = true)
@Data
public class ObjectBFileEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    private String attrItemFullCode;

    private Blob val;

}
