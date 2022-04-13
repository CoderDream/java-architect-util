package com.debug.middleware.server.controller.redis;

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.dto.RedPacketDto;
import com.debug.middleware.server.service.IRedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author: zhonglinsen
 * @date: 2019/3/15
 */
@RestController
public class RedPacketController {

    private static final Logger log= LoggerFactory.getLogger(RedPacketController.class);

    private static final String prefix="red/packet";

    @Autowired
    private IRedPacketService redPacketService;


    /**
     * 发
     */
    @RequestMapping(value = prefix+"/hand/out",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse handOut(@Validated @RequestBody RedPacketDto dto, BindingResult result){
       if (result.hasErrors()){
           return new BaseResponse(StatusCode.InvalidParams);
       }
       BaseResponse response=new BaseResponse(StatusCode.Success);
       try {
            String redId=redPacketService.handOut(dto);
            response.setData(redId);

       }catch (Exception e){
           log.error("发红包发生异常：dto={} ",dto,e.fillInStackTrace());
           response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
       }
       return response;
    }


    /**
     * 抢
     */
    @RequestMapping(value = prefix+"/rob",method = RequestMethod.GET)
    public BaseResponse rob(@RequestParam Integer userId, @RequestParam String redId){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            BigDecimal result=redPacketService.rob(userId,redId);
            if (result!=null){
                response.setData(result);
            }else{
                response=new BaseResponse(StatusCode.Fail.getCode(),"红包已被抢完!");
            }
        }catch (Exception e){
            log.error("抢红包发生异常：userId={} redId={}",userId,redId,e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
























