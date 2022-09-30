package com.example.demo.codesystem;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 发电流量
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {FdllTest.class}) //测试的class
@ContextConfiguration(classes = FdllTest.class)
public class FdllTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + OBJECT_DATA_PORT + "/api/object/data/fdll/";
    }

    @Test
    public void testQuery_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2022-01-01 00:00:00";
        String endTime = "2022-02-01 00:00:00";
        bodyMap.put("sendId", sendId);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        System.out.println(URI);
        result = postForObject(restTemplate, URI + "query", body);
        System.out.println(result);
    }

    @Test
    public void testQuery_02() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2022-01-01 00:00:00";
        String endTime = "2022-02-01 00:00:00";
        bodyMap.put("sendId", sendId);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "query", body);
        System.out.println(result);
    }

    @Test
    public void testQueryValue_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2022-01-01 00:00:00";
        String endTime = "2022-02-01 00:00:00";
        bodyMap.put("sendId", sendId);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        bodyMap.put("current", 1);
        bodyMap.put("size", 5);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "queryValue", body);
        System.out.println(result);
    }

    @Test
    public void testQueryAvgValue_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2022-01-01 00:00:00";
        String endTime = "2022-02-01 00:00:00";
        bodyMap.put("sendId", sendId);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        bodyMap.put("current", 1);
        bodyMap.put("size", 5);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "queryAvgValue", body);
        System.out.println(result);
    }

}
