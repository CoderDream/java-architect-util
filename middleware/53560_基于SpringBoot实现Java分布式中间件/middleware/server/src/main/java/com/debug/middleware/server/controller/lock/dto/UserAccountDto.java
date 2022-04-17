package com.debug.middleware.server.controller.lock.dto;/**
 * Created by Administrator on 2019/4/17.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * 用户账户提现申请dto
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/17 20:32
 **/
@Data
@ToString
public class UserAccountDto implements Serializable{
    private Integer userId; //用户账户Id
    private Double amount;  //提现金额
}