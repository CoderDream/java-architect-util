package com.coderdream.freeapps.ruoyi;

import cn.hutool.db.nosql.redis.RedisDS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Jedis 配置类
 */
@Configuration
public class JedisConfig {

    /**
     * 交由Spring来处理Bean对象创建Jedis
     * @return Jedis对象
     */
    @Bean
    public Jedis getJedis() {
        return RedisDS.create().getJedis();
    }
}

