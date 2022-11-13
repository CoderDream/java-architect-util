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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 发电流量
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {ObjectBStringTest.class}) //测试的class
@ContextConfiguration(classes = ObjectBStringTest.class)
public class ObjectBStringTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + OBJECT_DATA_PORT + "/api/object/data/objectBString/";
    }

    @Test
    public void testSelectBatchByPks_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> queryBodyMap = new LinkedHashMap<String, Object>();
        String objectFullCode= "0111130000000"; //  // "attrFullCode": "010000B010300",
        String attrItemFullCode = "011113B020101";
        queryBodyMap.put("attrItemFullCode", attrItemFullCode);
        queryBodyMap.put("objectFullCode", objectFullCode);
        List<String> list = new ArrayList<>();
//        list.add("010000B00A700");
        list.add("010005B010100");
        list.add("010005B010200");
        //String attrItemFullCode1 = "010005B010100";
//        list.add("010002B020301");

        String body = JSONObject.toJSONString(list);
        System.out.println(body);
        URI += "selectBatchByPks";
        System.out.println(URI);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }

    @Test
    public void testInsertBatch_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();

        String attrItemFullCode1 = "010002B00A701";
        String value1= "146";

        String attrItemFullCode2 = "010002B020201";
        String value2= "60106981";

        Map<String, String> idMap1 = new LinkedHashMap<>();
        idMap1.put("attrItemFullCode", attrItemFullCode1);
        idMap1.put("value", value1);
        Map<String, String> idMap2 = new LinkedHashMap<>();
        idMap2.put("attrItemFullCode", attrItemFullCode2);
        idMap2.put("value", value2);
        Object[] objects = new Object[2];
        objects[0] = idMap1;
        objects[1] = idMap2;
        String body = JSONObject.toJSONString(objects);
        System.out.println("body: " + body);
        URI += "insertBatch";
        System.out.println(URI);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }


    @Test
    public void testList_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> queryBodyMap = new LinkedHashMap<String, Object>();
        String objectFullCode1= "0100050000000";
        String attrItemFullCode1 = "010005B010100";
        queryBodyMap.put("objectFullCode", objectFullCode1);
        queryBodyMap.put("attrItemFullCode", attrItemFullCode1);


//        String objectFullCode2= "0100050000000";
//        String attrItemFullCode2 = "010005B010200";
//
//        Map<String, String> idMap1 = new LinkedHashMap<>();
//        idMap1.put("objectFullCode", objectFullCode1);
//        idMap1.put("attrItemFullCode", attrItemFullCode1);
//        Map<String, String> idMap2 = new LinkedHashMap<>();
//        idMap2.put("objectFullCode", objectFullCode2);
//        idMap2.put("attrItemFullCode", attrItemFullCode2);
//        Object[] objects = new Object[2];
//        objects[0] = idMap1;
//        objects[1] = idMap2;
//        String body = JSONObject.toJSONString(objects);
        String body = JSONObject.toJSONString(queryBodyMap);
        System.out.println("body: " + body);

        System.out.println(body);
        System.out.println(URI);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
    }

}
