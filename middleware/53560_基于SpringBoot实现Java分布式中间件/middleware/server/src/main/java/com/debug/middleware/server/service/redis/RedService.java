package com.debug.middleware.server.service.redis;

import com.debug.middleware.model.entity.RedDetail;
import com.debug.middleware.model.entity.RedRecord;
import com.debug.middleware.model.entity.RedRobRecord;
import com.debug.middleware.model.mapper.RedDetailMapper;
import com.debug.middleware.model.mapper.RedRecordMapper;
import com.debug.middleware.model.mapper.RedRobRecordMapper;
import com.debug.middleware.server.dto.RedPacketDto;
import com.debug.middleware.server.service.IRedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: zhonglinsen
 * @date: 2019/3/18
 */
@Service
@EnableAsync
public class RedService implements IRedService {

    private static final Logger log= LoggerFactory.getLogger(RedService.class);

    @Autowired
    private RedRecordMapper redRecordMapper;

    @Autowired
    private RedDetailMapper redDetailMapper;

    @Autowired
    private RedRobRecordMapper redRobRecordMapper;


    /**
     * 发红包记录
     * @param dto
     * @param redId
     * @param list
     * @throws Exception
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception {
        RedRecord redRecord=new RedRecord();
        redRecord.setUserId(dto.getUserId());
        redRecord.setRedPacket(redId);
        redRecord.setTotal(dto.getTotal());
        redRecord.setAmount(BigDecimal.valueOf(dto.getAmount()));
        redRecordMapper.insertSelective(redRecord);

        RedDetail detail;
        for (Integer i:list){
            detail=new RedDetail();
            detail.setRecordId(redRecord.getId());
            detail.setAmount(BigDecimal.valueOf(i));
            redDetailMapper.insertSelective(detail);
        }
    }

    /**
     * 抢红包记录
     * @param userId
     * @param redId
     * @param amount
     * @throws Exception
     */
    @Override
    @Async
    public void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception {
        RedRobRecord redRobRecord=new RedRobRecord();
        redRobRecord.setUserId(userId);
        redRobRecord.setRedPacket(redId);
        redRobRecord.setAmount(amount);
        redRobRecord.setRobTime(new Date());
        redRobRecordMapper.insertSelective(redRobRecord);
    }
}









































