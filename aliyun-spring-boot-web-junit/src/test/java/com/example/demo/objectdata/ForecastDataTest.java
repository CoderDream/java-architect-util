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
@SpringBootTest(classes = {ForecastDataTest.class}) //测试的class
@ContextConfiguration(classes = ForecastDataTest.class)
public class ForecastDataTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + OBJECT_DATA_PORT + "/api/object/data/forecast/data/";
    }

    @Test
    public void testQuery_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> queryBodyMap = new LinkedHashMap<String, Object>();
        String objectFullCode= "01 1113 0 00 00 00";
        String attrItemFullCode = "01 1113 B 02 01 01";
        queryBodyMap.put("attrItemFullCode", attrItemFullCode);
        queryBodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(queryBodyMap);
        System.out.println(body);
        System.out.println(URI);
        result = postForObject(restTemplate, URI + "listForecastDetails", body);
        System.out.println(result);
    }

    @Test
    public void testQuery_02() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        String body = "{\"attrItemFullCode\":\"020005D020501\",\"beginTime\":\"2000-01-01 00:00:00\",\"endTime\":\"2022-11-03 16:33:30\"}";
        System.out.println(body);
        System.out.println(URI);
        result = postForObject(restTemplate, URI + "listForecastDetails", body);
        System.out.println(result);
    }


    @Test
    public void testList_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> queryBodyMap = new LinkedHashMap<String, Object>();
        String objectFullCode1= "01 1113 0 00 00 00";
        String attrItemFullCode1 = "01 1113 B 02 01 01";

        String objectFullCode2= "01 1113 0 00 00 00";
        String attrItemFullCode2 = "01 1113 B 02 02 01";

        Map<String, String> idMap1 = new LinkedHashMap<>();
        idMap1.put("objectFullCode", objectFullCode1);
        idMap1.put("attrItemFullCode", attrItemFullCode1);
        Map<String, String> idMap2 = new LinkedHashMap<>();
        idMap2.put("objectFullCode", objectFullCode2);
        idMap2.put("attrItemFullCode", attrItemFullCode2);
        Object[] objects = new Object[2];
        objects[0] = idMap1;
        objects[1] = idMap2;
        String body = JSONObject.toJSONString(objects);
        System.out.println("body: " + body);

        System.out.println(body);
        System.out.println(URI);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
    }

}
