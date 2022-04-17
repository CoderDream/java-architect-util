package com.debug.middleware.server.service.redis;

import com.debug.middleware.model.entity.Item;
import com.debug.middleware.model.mapper.ItemMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存穿透service
 * Created by Administrator on 2019/3/17.
 */
@Service
public class CachePassService {

    private static final Logger log= LoggerFactory.getLogger(CachePassService.class);

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String keyPrefix="item:";

    /**
     * 获取商品详情-如果缓存有，则从缓存中获取；如果没有，则从数据库查询，并将查询结果塞入缓存中
     * @param itemCode
     * @return
     * @throws Exception
     */
    public Item getItemInfo(String itemCode) throws Exception{
        Item item=null;

        final String key=keyPrefix+itemCode;
        ValueOperations valueOperations=redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            log.info("---获取商品详情-缓存中存在该商品---商品编号为：{} ",itemCode);

            //从缓存中查询该商品详情
            Object res=valueOperations.get(key);
            if (res!=null && !Strings.isNullOrEmpty(res.toString())){
                item=objectMapper.readValue(res.toString(),Item.class);
            }
        }else{
            log.info("---获取商品详情-缓存中不存在该商品-从数据库中查询---商品编号为：{} ",itemCode);

            //从数据库中获取该商品详情
            item=itemMapper.selectByCode(itemCode);
            if (item!=null){
                valueOperations.set(key,objectMapper.writeValueAsString(item));
            }else{
                //过期失效时间TTL设置为30分钟-当然实际情况要根据实际业务决定
                valueOperations.set(key,"",30L, TimeUnit.MINUTES);
            }
        }
        return item;
    }
}


























