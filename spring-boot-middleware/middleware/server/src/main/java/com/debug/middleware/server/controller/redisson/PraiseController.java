package com.debug.middleware.server.controller.redisson;

import com.debug.middleware.api.enums.StatusCode;
import com.debug.middleware.api.response.BaseResponse;
import com.debug.middleware.server.dto.PraiseDto;
import com.debug.middleware.server.service.IPraiseService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * 博客点赞Controller
 * @author: zhonglinsen
 */
@RestController
public class PraiseController {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(PraiseController.class);
    //定义请求前缀
    private static final String prefix="blog/praise";
    //定义点赞服务处理实例
    @Autowired
    private IPraiseService praiseService;

    /**
     * 点赞
     * @return
     */
    @RequestMapping(value = prefix+"/add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse addPraise(@RequestBody @Validated PraiseDto dto, BindingResult result){
        //校验参数的合法性
        if (result.hasErrors()){
            //如果有参数不合法-则直接返回错误的响应信息
            return new BaseResponse(StatusCode.InvalidParams);
        }
        //定义响应结果实例
        BaseResponse response=new BaseResponse(StatusCode.Success);
        //定义返回的响应数据
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            //调用点赞博客处理方法
            //praiseService.addPraise(dto);
            praiseService.addPraiseLock(dto);

            //获取博客的总点赞数
            Long total=praiseService.getBlogPraiseTotal(dto.getBlogId());
            //将结果塞入响应数据中
            resMap.put("praiseTotal",total);
        }catch (Exception e){
            log.error("点赞博客-发生异常：{} ",dto,e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }


    /**
     * 取消点赞
     * @return
     */
    @RequestMapping(value = prefix+"/cancel",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse cancelPraise(@RequestBody @Validated PraiseDto dto, BindingResult result){
        //校验参数的合法性
        if (result.hasErrors()){
            //如果有参数不合法-则直接返回错误的响应信息
            return new BaseResponse(StatusCode.InvalidParams);
        }
        //定义响应结果实例
        BaseResponse response=new BaseResponse(StatusCode.Success);
        //定义返回的响应数据
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            //调用取消点赞博客处理方法
            praiseService.cancelPraise(dto);

            //获取博客的总点赞数
            Long total=praiseService.getBlogPraiseTotal(dto.getBlogId());
            //将结果塞入响应数据中
            resMap.put("praiseTotal",total);
        }catch (Exception e){
            log.error("取消点赞博客 发生异常：{} ",dto,e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 获取博客点赞排行榜
     * @return
     */
    @RequestMapping(value = prefix+"/total/rank",method = RequestMethod.GET)
    public BaseResponse rankPraise(){
        //定义响应结果实例
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(praiseService.getRankWithRedisson());

        }catch (Exception e){
            log.error("获取博客点赞排行榜-发生异常: ",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}











































