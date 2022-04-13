package com.debug.middleware.model.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 用户账户实体
 */
@Data
@ToString
public class UserAccount {
    private Integer id; //主键Id
    private Integer userId;//用户账户id
    private BigDecimal amount;//账户余额
    private Integer version; //版本号
    private Byte isActive; //是否有效账户
}