package com.company.microserviceuser.controller;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.alibaba.fastjson.JSON;
import com.company.microserviceuser.dto.*;
import com.company.microserviceuser.vo.common.*;


/**
 * Redis操作接口，测试
 * @author xindaqi
 * @since 2020-11-25
 */
@CrossOrigin(origins="*", maxAge=3600) 
@RestController 
@RequestMapping("/api/redis")
@Api(tags = "Redis增删改查")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired 
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;


    @PostMapping(value="/object/add")
    @ApiOperation("Object-Redis添加数据")
    @ApiImplicitParam(name="params", value="用户信息", dataType="QueryUserByPageInputDTO", paramType="body")
    public ResponseVO<QueryUserByPageInputDTO> addDataInRedis(@RequestBody QueryUserByPageInputDTO params) {
        if(null != redisTemplate.opsForValue().get(params.getUsername())) {
            logger.info("成功--从Redis取数据");
            QueryUserByPageInputDTO queryUserByPageInputDTO = (QueryUserByPageInputDTO)redisTemplate.opsForValue().get(params.getUsername());
            return new ResponseVO<QueryUserByPageInputDTO>().ok(queryUserByPageInputDTO);

        }
        redisTemplate.opsForValue().set(params.getUsername(), params, 180, TimeUnit.SECONDS);
        logger.info("成功--存储Redis");
        return new ResponseVO<QueryUserByPageInputDTO>().ok(params);
    }

    @DeleteMapping(value="/object/delete")
    @ApiOperation("Object-Redis删除数据")
    @ApiImplicitParam(name="username", value="用户姓名", dataType="String", paramType="query")
    public ResponseVO<String> deleteDateInRedis(@RequestParam("username") String username) {
        boolean isDelete = redisTemplate.delete(username);
        if(isDelete) {
            logger.info("成功--删除Redis数据");
            return new ResponseVO<String>().ok();
        }
        logger.info("失败--删除Redis数据");
        return new ResponseVO<String>().fail();
    }

    @PutMapping(value="/object/edit")
    @ApiOperation("Object-Redis修改数据")
    @ApiImplicitParam(name="params", value="用户信息", dataType="QueryUserByPageInputDTO", paramType="body")
    public ResponseVO<String> editDataInRedis(@RequestBody QueryUserByPageInputDTO params) {
        redisTemplate.opsForValue().set(params.getUsername(), params, 180, TimeUnit.SECONDS);
        return new ResponseVO<String>().ok();
    }

    @GetMapping(value="/object/query")
    @ApiOperation("Object-Redis查询数据")
    @ApiImplicitParam(name="username", value="用户名", dataType="String", paramType="query")
    public ResponseVO<QueryUserByPageInputDTO> queryDataInRedis(@RequestParam("username") String username) {
        if(null != redisTemplate.opsForValue().get(username)) {
            return new ResponseVO<QueryUserByPageInputDTO>().ok((QueryUserByPageInputDTO)redisTemplate.opsForValue().get(username));
        }
        return new ResponseVO<QueryUserByPageInputDTO>().ok();
    }


    @PostMapping(value="/string/add")
    @ApiOperation("String-Redis添加数据")
    @ApiImplicitParam(name="params", value="用户信息", dataType="QueryUserByPageInputDTO", paramType="body")
    public ResponseVO<QueryUserByPageInputDTO> addStringInRedis(@RequestBody QueryUserByPageInputDTO params) {
        if(null != redisTemplate.opsForValue().get(params.getUsername())) {
            logger.info("成功--从Redis取数据");
            QueryUserByPageInputDTO queryUserByPageInputDTO = JSON.parseObject(stringRedisTemplate.opsForValue().get(params.getUsername()), QueryUserByPageInputDTO.class);
            return new ResponseVO<QueryUserByPageInputDTO>().ok(queryUserByPageInputDTO);
        }
        stringRedisTemplate.opsForValue().set(params.getUsername(), JSON.toJSONString(params), 180, TimeUnit.SECONDS);
        logger.info("成功--存储Redis");
        return new ResponseVO<QueryUserByPageInputDTO>().ok(params);
    }

    @DeleteMapping(value="/string/delete")
    @ApiOperation("String-Redis删除数据")
    @ApiImplicitParam(name="username", value="用户姓名", dataType="String", paramType="query")
    public ResponseVO<String> deleteStringInRedis(@RequestParam("username") String username) {
        boolean isDelete = stringRedisTemplate.delete(username);
        if(isDelete) {
            logger.info("成功--删除Redis数据");
            return new ResponseVO<String>().ok();
        }
        logger.info("失败--删除Redis数据");
        return new ResponseVO<String>().fail();
    }

    @PutMapping(value="/string/edit")
    @ApiOperation("String-Redis修改数据")
    @ApiImplicitParam(name="params", value="用户信息", dataType="QueryUserByPageInputDTO", paramType="body")
    public ResponseVO<String> editStringInRedis(@RequestBody QueryUserByPageInputDTO params) {
        stringRedisTemplate.opsForValue().set(params.getUsername(), JSON.toJSONString(params), 180, TimeUnit.SECONDS);
        return new ResponseVO<String>().ok();
    }

    @GetMapping(value="/string/query")
    @ApiOperation("String-Redis查询数据")
    @ApiImplicitParam(name="username", value="用户名", dataType="String", paramType="query")
    public ResponseVO<QueryUserByPageInputDTO> queryStringInRedis(@RequestParam("username") String username) {
        if(null != redisTemplate.opsForValue().get(username)) {
            return new ResponseVO<QueryUserByPageInputDTO>().ok(JSON.parseObject(stringRedisTemplate.opsForValue().get(username), QueryUserByPageInputDTO.class));
        }
        return new ResponseVO<QueryUserByPageInputDTO>().ok();
    }
 
}