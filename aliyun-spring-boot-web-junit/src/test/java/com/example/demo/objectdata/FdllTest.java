package com.example.demo.objectdata;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.codesystem.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void testQuery_03() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrItemFullCode = "12 0002 D 06 01 01"; //"": "",
//        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2009-01-01 00:00:00";
        String endTime = "2022-02-01 00:00:00";
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        URI += "query";
        System.out.println(URI);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }

    /**
     * {
     * 	"attrItemFullCode": "01 0005 D 03 02 01",
     * 	"period": "hour",
     * 	"beginTime": "2000-01-01 00:00:00",
     * 	"endTime": "2022-10-21 19:01:07"
     * }
     */
    @Test
    public void testQuery_04() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrItemFullCode = "01 0005 D 03 02 01"; //"": "",
//        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2000-01-01 00:00:00";
        String endTime = "2022-10-21 19:01:07";
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        URI += "query";
        System.out.println(URI);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }

    /**
     * {
     * 	"attrItemFullCode": "16 0001 D 0E A1 01",
     * 	"period": "hour",
     * 	"beginTime": "2000-01-01 00:00:00",
     * 	"endTime": "2022-10-23 18:17:11"
     * }
     */
    @Test
    public void testQuery_05() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrItemFullCode = "02 0005 D 07 01 01"; //"": "",
//        String sendId = "1563000008";
        String period = "hour";
        String beginTime = "2000-01-01 00:00:00";
        String endTime = "2022-10-24 17:35:37";
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("period", period);
        bodyMap.put("beginTime", beginTime);
        bodyMap.put("endTime", endTime);
        String body = JSONObject.toJSONString(bodyMap);
        body = "{\"attrItemFullCode\":\"020005D020501\",\"period\":\"hour\",\"beginTime\":\"2000-01-01 00:00:00\",\"endTime\":\"2022-11-03 15:03:55\"}";
        System.out.println(body);
        URI += "query";
        System.out.println(URI);
        result = postForObject(restTemplate, URI, body);
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
