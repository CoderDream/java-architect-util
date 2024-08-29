package com.coderdream.freeapps.ruoyi;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/TestJedis")
@Api(tags = "redis测试Controller")
public class TestJedis {

    @Autowired
    private Jedis jedis;

    @GetMapping("/setJedis")
    @ApiOperation("使用Jedis存入数据到redis")
    public R testSetJedis() {
        try {
            jedis.set("1212", "redis存入去除这么简单");
        } catch (Exception e) {
            System.out.println(e);
            return R.fail("redis存入失败");
        }
        return R.ok("存入成功");
    }

    @GetMapping("/getJedis")
    @ApiOperation("使用Jedis从redis获取数据")
    public R testGetJedis() {
        String s = null;
        try {
            s = jedis.get("1212");
        } catch (Exception e) {
            System.out.println(e);
            return R.fail("redis取出失败");
        }
        return R.ok(s, "获取成功");
    }
}
