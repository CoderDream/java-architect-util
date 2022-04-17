package com.debug.middleware.server.controller.lock;/**
 * Created by Administrator on 2019/4/17.
 */

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.controller.lock.dto.BookRobDto;
import com.debug.middleware.server.service.lock.BookRobService;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书籍抢购Controller
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/21 23:31
 **/
@RestController
public class BookRobController {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(BookRobController.class);
    //定义请求前缀
    private static final String prefix="book/rob";
    //定义核心逻辑处理服务类
    @Autowired
    private BookRobService bookRobService;

    /**
     * 用户抢购书籍请求
     * @param dto
     * @return
     */
    @RequestMapping(value = prefix+"/request",method = RequestMethod.GET)
    public BaseResponse takeMoney(BookRobDto dto){
        if (Strings.isNullOrEmpty(dto.getBookNo()) || dto.getUserId()==null || dto.getUserId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //不加锁的情况
            //bookRobService.robWithNoLock(dto);

            //加ZooKeeper分布式锁的情况
            //bookRobService.robWithZKLock(dto);

            //加Redisson分布式锁的情况
            bookRobService.robWithRedisson(dto);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}













































