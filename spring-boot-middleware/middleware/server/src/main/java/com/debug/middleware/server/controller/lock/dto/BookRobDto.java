package com.debug.middleware.server.controller.lock.dto;/**
 * Created by Administrator on 2019/4/17.
 */

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * 书籍抢购实体dto
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/17 20:32
 **/
@Data
@ToString
public class BookRobDto implements Serializable{
    private Integer userId;//用户id
    private String bookNo; //书籍编号
}