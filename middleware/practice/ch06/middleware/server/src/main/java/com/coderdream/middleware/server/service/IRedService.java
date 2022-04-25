package com.coderdream.middleware.server.service;

import com.coderdream.middleware.server.dto.RedPacketDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 红包记录服务
 */
public interface IRedService {
    /**
     * 发红包记录，异步的方式
     *
     * @param dto 红包总金额+个数
     * @param redId 红包全局唯一标识串
     * @param list 红包随机金额列表
     * @throws Exception
     */
    void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception;

    /**
     * 抢红包记录
     * 成功抢到红包时将当前用户账号信息以及对应的红包金额等信息记录进数据库中
     *
     * @param userId 用户 ID
     * @param redId 红包全局唯一标识串
     * @param amount 抢到的红包金额
     * @throws Exception
     */
    void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception;

}
