package com.coderdream.middleware.server.controller.redis;

import com.coderdream.middleware.api.enums.StatusCode;
import com.coderdream.middleware.api.response.BaseResponse;
import com.coderdream.middleware.server.dto.RedPacketDto;
import com.coderdream.middleware.server.service.IRedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class RedPacketController {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(RedPacketController.class);

    /**
     * 定义请求路径的前缀
     */
    private static final String prefix = "red/packet";

    /**
     * 注入红包业务逻辑处理接口服务
     */
    @Resource
    private IRedPacketService redPacketService;

    /**
     * 发红包请求，请求方法为 POST，请求参数采用 JSON 格式进行提交
     */
    @RequestMapping(value = prefix + "/hand/out", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse handOut(@Validated @RequestBody RedPacketDto dto, BindingResult result) {
        // 参数校验
        if (result.hasErrors()) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            // 核心业务逻辑处理服务，最终返回红包全局唯一标识串
            String redId = redPacketService.handOut(dto);
            // 将红包全局唯一标识串返回前端
            response.setData(redId);
        } catch (Exception e) {
            // 如果报异常，则输出日志并返回相应的错误信息
            log.error("发红包发生异常：dto={} ", dto, e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * 处理抢红包请求：接收当前用户 ID 和红包全局唯一标识串参数
     */
    @RequestMapping(value = prefix + "/rob", method = RequestMethod.GET)
    public BaseResponse rob(@RequestParam Integer userId, @RequestParam String redId) {
        // 定义相应对象
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            // 调用红包业务逻辑处理接口中的抢红包方法，最终返回抢到的红包的金额
            // 单位为元（不为Null时表示抢到了，否则代表已经被抢完了
            BigDecimal result = redPacketService.rob(userId, redId);
            if (result != null) {
                // 将抢到的红包金额返回到前端
                response.setData(result);
            } else {
                // 没有抢到红包，即已经被抢完了
                response = new BaseResponse(StatusCode.Fail.getCode(), "红包已被抢完!");
            }
        } catch (Exception e) {
            // 处理过程如果发生异常，则输出异常信息并返回给前端
            log.error("抢红包发生异常：userId={} redId={}", userId, redId, e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        // 返回处理结果给前端
        return response;
    }
}
