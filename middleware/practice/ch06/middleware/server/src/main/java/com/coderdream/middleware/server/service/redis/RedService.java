package com.coderdream.middleware.server.service.redis;

import com.coderdream.middleware.model.entity.RedDetail;
import com.coderdream.middleware.model.entity.RedRecord;
import com.coderdream.middleware.model.entity.RedRobRecord;
import com.coderdream.middleware.model.mapper.RedDetailMapper;
import com.coderdream.middleware.model.mapper.RedRecordMapper;
import com.coderdream.middleware.model.mapper.RedRobRecordMapper;
import com.coderdream.middleware.server.dto.RedPacketDto;
import com.coderdream.middleware.server.service.IRedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@EnableAsync
public class RedService implements IRedService {

    private static final Logger log = LoggerFactory.getLogger(RedService.class);

    @Resource
    private RedRecordMapper redRecordMapper;

    @Resource
    private RedDetailMapper redDetailMapper;

    @Resource
    private RedRobRecordMapper redRobRecordMapper;

    /**
     * 发红包记录
     *
     * @param dto   红包总金额+个数
     * @param redId 红包全局唯一标识串
     * @param list  红包随机金额列表
     * @throws Exception
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception {
        // 定义实体类对象
        RedRecord redRecord = new RedRecord();
        // 设置字段的取值信息
        redRecord.setUserId(dto.getUserId());
        redRecord.setRedPacket(redId);
        redRecord.setTotal(dto.getTotal());
        redRecord.setAmount(BigDecimal.valueOf(dto.getAmount()));
        redRecord.setCreateTime(new Date());
        // 将对象信息插入数据库
        redRecordMapper.insertSelective(redRecord);
        // 定义红包随机金额明细实体类对象
        RedDetail detail;
        // 遍历随机金额列表，将金额等信息设置到相应的字段中
        for (Integer i : list) {
            detail = new RedDetail();
            detail.setRecordId(redRecord.getId());
            detail.setAmount(BigDecimal.valueOf(i));
            detail.setCreateTime(new Date());
            // 将对象信息插入数据库
            redDetailMapper.insertSelective(detail);
        }
    }

    /**
     * 抢红包记录
     * 成功抢到红包时将当前用户账号信息以及对应的红包金额等信息记录进数据库中
     *
     * @param userId 用户 ID
     * @param redId  红包全局唯一标识串
     * @param amount 抢到的红包金额
     * @throws Exception
     */
    @Override
    @Async
    public void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception {
        // 定义记录抢到红包时录入相关信息的实体对象，并设置相应字段的取值
        RedRobRecord redRobRecord = new RedRobRecord();
        redRobRecord.setUserId(userId); // 设置用户 ID
        redRobRecord.setRedPacket(redId); // 设置红包全局唯一标识串
        redRobRecord.setAmount(amount); // 设置抢到的红包金额
        redRobRecord.setRobTime(new Date());// 设置抢到的时间
        // 将实体对象信息插入数据库中
        redRobRecordMapper.insertSelective(redRobRecord);
    }
}