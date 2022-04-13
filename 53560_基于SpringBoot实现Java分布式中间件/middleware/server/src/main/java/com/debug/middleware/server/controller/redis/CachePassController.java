package com.debug.middleware.server.controller.redis;/**
 * Created by Administrator on 2019/3/17.
 */

import com.debug.middleware.server.service.redis.CachePassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存穿透实战
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/17 18:33
 **/
@RestController
public class CachePassController {

    private static final Logger log= LoggerFactory.getLogger(CachePassController.class);

    private static final String prefix="cache/pass";

    @Autowired
    private CachePassService cachePassService;


    /**
     * 获取热销商品信息
     * @param itemCode
     * @return
     */
    @RequestMapping(value = prefix+"/item/info",method = RequestMethod.GET)
    public Map<String,Object> getItem(@RequestParam String itemCode){
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("code",0);
        resMap.put("msg","成功");

        try {
            resMap.put("data",cachePassService.getItemInfo(itemCode));
        }catch (Exception e){
            resMap.put("code",-1);
            resMap.put("msg","失败"+e.getMessage());
        }
        return resMap;
    }
}


























































