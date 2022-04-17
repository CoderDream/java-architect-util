package com.coderdream.middleware.server.service;


import com.coderdream.middleware.server.dto.RedPacketDto;

import java.math.BigDecimal;

/**
 */
public interface IRedPacketService {

    /**
     * 发红包
     *
     * @param dto 红包总金额+个数
     * @return
     * @throws Exception
     */
    String handOut(RedPacketDto dto) throws Exception;

    /**
     * 抢红包
     *
     * @param userId 当前用户 ID
     * @param redId 红包全局唯一标识串
     * @return
     * @throws Exception
     */
    BigDecimal rob(Integer userId, String redId) throws Exception;
}
