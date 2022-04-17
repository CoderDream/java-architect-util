package com.coderdream.middleware.server.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 */
@Data
@ToString
public class RedPacketDto {
    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 红包个数
     */
    @NotNull
    private Integer total;

    /**
     * 总金额-单位为分
     */
    @NotNull
    private Integer amount;
}