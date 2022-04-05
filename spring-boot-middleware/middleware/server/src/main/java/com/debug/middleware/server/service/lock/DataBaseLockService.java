package com.debug.middleware.server.service.lock;/**
 * Created by Administrator on 2019/4/17.
 */

import com.debug.middleware.model.entity.UserAccount;
import com.debug.middleware.model.entity.UserAccountRecord;
import com.debug.middleware.model.mapper.UserAccountMapper;
import com.debug.middleware.model.mapper.UserAccountRecordMapper;
import com.debug.middleware.server.controller.lock.dto.UserAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 基于数据库级别的乐观、悲观锁服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/17 20:36
 **/
@Service
public class DataBaseLockService {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(DataBaseLockService.class);
    //定义“用户账户余额实体”Mapper操作接口
    @Autowired
    private UserAccountMapper userAccountMapper;
    //定义“用户成功申请提现时金额记录”Mapper操作接口
    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    /**
     * 用户账户提取金额处理
     * @param dto
     * @throws Exception
     */
    public void takeMoney(UserAccountDto dto) throws Exception{
        //查询用户账户实体记录
        UserAccount userAccount=userAccountMapper.selectByUserId(dto.getUserId());
        //判断实体记录是否存在 以及 账户余额是否足够被提现
        if (userAccount!=null && userAccount.getAmount().doubleValue()-dto.getAmount()>0){
            //如果足够被提现，则更新现有的账户余额
            userAccountMapper.updateAmount(dto.getAmount(),userAccount.getId());

            //同时记录提现成功时的记录
            UserAccountRecord record=new UserAccountRecord();
            //设置提现成功时的时间
            record.setCreateTime(new Date());
            //设置账户记录主键id
            record.setAccountId(userAccount.getId());
            //设置成功申请提现时的金额
            record.setMoney(BigDecimal.valueOf(dto.getAmount()));
            //插入申请提现金额历史记录
            userAccountRecordMapper.insert(record);
            //打印日志
            log.info("当前待提现的金额为：{} 用户账户余额为：{}",dto.getAmount(),userAccount.getAmount());
        }else {
            throw new Exception("账户不存在或账户余额不足!");
        }
    }

    /**
     * 乐观锁处理方式
     * @param dto
     * @throws Exception
     */
    public void takeMoneyWithLock(UserAccountDto dto) throws Exception{
        //查询用户账户实体记录
        UserAccount userAccount=userAccountMapper.selectByUserId(dto.getUserId());
        //判断实体记录是否存在 以及 账户余额是否足够被提现
        if (userAccount!=null && userAccount.getAmount().doubleValue()-dto.getAmount()>0){
            //如果足够被提现，则更新现有的账户余额 - 采用version版本号机制
            int res=userAccountMapper.updateByPKVersion(dto.getAmount(),userAccount.getId(),userAccount.getVersion());
            //只有当更新成功时(此时res=1，即数据库执行更细语句之后数据库受影响的记录行数)
            if (res>0){
                //同时记录提现成功时的记录
                UserAccountRecord record=new UserAccountRecord();
                //设置提现成功时的时间
                record.setCreateTime(new Date());
                //设置账户记录主键id
                record.setAccountId(userAccount.getId());
                //设置成功申请提现时的金额
                record.setMoney(BigDecimal.valueOf(dto.getAmount()));
                //插入申请提现金额历史记录
                userAccountRecordMapper.insert(record);
                //打印日志
                log.info("当前待提现的金额为：{} 用户账户余额为：{}",dto.getAmount(),userAccount.getAmount());
            }
        }else {
            throw new Exception("账户不存在或账户余额不足!");
        }
    }


    /**
     * 悲观锁处理方式
     * @param dto
     * @throws Exception
     */
    public void takeMoneyWithLockNegative(UserAccountDto dto) throws Exception{
        //查询用户账户实体记录 - for update的方式
        UserAccount userAccount=userAccountMapper.selectByUserIdLock(dto.getUserId());
        //判断实体记录是否存在 以及 账户余额是否足够被提现
        if (userAccount!=null && userAccount.getAmount().doubleValue()-dto.getAmount()>0){
            //如果足够被提现，则更新现有的账户余额 - 采用version版本号机制
            int res=userAccountMapper.updateAmountLock(dto.getAmount(),userAccount.getId());
            //只有当更新成功时(此时res=1，即数据库执行更细语句之后数据库受影响的记录行数)
            if (res>0){
                //同时记录提现成功时的记录
                UserAccountRecord record=new UserAccountRecord();
                //设置提现成功时的时间
                record.setCreateTime(new Date());
                //设置账户记录主键id
                record.setAccountId(userAccount.getId());
                //设置成功申请提现时的金额
                record.setMoney(BigDecimal.valueOf(dto.getAmount()));
                //插入申请提现金额历史记录
                userAccountRecordMapper.insert(record);
                //打印日志
                log.info("悲观锁处理方式-当前待提现的金额为：{} 用户账户余额为：{}",dto.getAmount(),userAccount.getAmount());
            }
        }else {
            throw new Exception("悲观锁处理方式-账户不存在或账户余额不足!");
        }
    }
}
































