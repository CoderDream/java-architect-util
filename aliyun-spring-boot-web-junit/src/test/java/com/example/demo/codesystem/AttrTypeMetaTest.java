package com.example.demo.codesystem;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {AttrTypeMetaTest.class}) //测试的class
@ContextConfiguration(classes = AttrTypeMetaTest.class)
public class AttrTypeMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-type-meta/";
    }

    @Test
    public void testCRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap;
        String body;
        String code;
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String structureTypeCode = "B";
        Integer commonFlag = 0;

        bodyMap = new LinkedHashMap<>();
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        body = JSONObject.toJSONString(bodyMap);

        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        code = getCode(result);
        String attrTypeName = "属性类型" + code;
        String attrTypeLabel = "TTSSEEAA";
        String attrTypeCode = code;
        String attrTypeFullCode = "01 0000 0 00 " + code + " 00";
        String remark = "属性类型 " + code + "描述";

        bodyMap.put("attrTypeName", attrTypeName);
        bodyMap.put("attrTypeLabel", attrTypeLabel);
        bodyMap.put("attrTypeCode", attrTypeCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        bodyMap.put("remark", "测试属性类型描述" + remark);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        System.out.println(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrTypeFullCode", attrTypeFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);

        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            String newAttrTypeName = "";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("attrTypeName", newAttrTypeName);
            updateBodyMap.put("attrTypeLabel", attrTypeLabel);
            updateBodyMap.put("attrTypeCode", attrTypeCode);
            updateBodyMap.put("attrTypeFullCode", attrTypeFullCode);
            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            updateBodyMap.put("structureTypeCode", structureTypeCode);
            updateBodyMap.put("commonFlag", commonFlag);
            updateBodyMap.put("remark", "测试属性类型描述2" + code);
            body = JSONObject.toJSONString(updateBodyMap);
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            System.out.println("删除成功：" + id);
        }
    }


    @Test
    public void testList_04() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        String body;
        Integer commonFlag = 0;

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("commonFlag", commonFlag);
        URI += "list";
        System.out.println(URI);
        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);

    }

    @Test
    public void testList_05() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        String body;
        Integer commonFlag = 1;

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("commonFlag", commonFlag);
        URI += "list";
        System.out.println(URI);
        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }

    @Test
    public void testList_06() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        String body;
        Integer commonFlag = 1;

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 100);
        URI += "list";
        System.out.println(URI);
        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI, body);
        System.out.println(result);
    }

    @Test
    public void testCRUD_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap;
        String body;
        String code;
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String structureTypeCode = "B";
        Integer commonFlag = 1;

        bodyMap = new LinkedHashMap<>();
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        body = JSONObject.toJSONString(bodyMap);

        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        code = getCode(result);
        String attrTypeName = "属性类型" + code;
        String attrTypeLabel = "TTSSEEAAEE";
        String attrTypeCode = code;
        String attrTypeFullCode = "01 0000 B " + code + " 00 00";
        String remark = "属性类型 " + code + "描述";

        bodyMap.put("attrTypeName", attrTypeName);
        bodyMap.put("attrTypeLabel", attrTypeLabel);
        bodyMap.put("attrTypeCode", attrTypeCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        bodyMap.put("remark", "测试属性类型描述" + remark);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        System.out.println(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrTypeFullCode", attrTypeFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);

        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            String newAttrTypeName = "";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("attrTypeName", newAttrTypeName);
            updateBodyMap.put("attrTypeLabel", attrTypeLabel);
            updateBodyMap.put("attrTypeCode", attrTypeCode);
            updateBodyMap.put("attrTypeFullCode", attrTypeFullCode);
            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            updateBodyMap.put("structureTypeCode", structureTypeCode);
            updateBodyMap.put("commonFlag", commonFlag);
            updateBodyMap.put("remark", "测试属性类型描述2" + code);
            body = JSONObject.toJSONString(updateBodyMap);
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            System.out.println("删除成功：" + id);
        }
    }

    @Test
    public void testCRUD_03() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap;
        String body;
        String code;
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String structureTypeCode = "B";
        Integer commonFlag = 0;

        bodyMap = new LinkedHashMap<>();
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        body = JSONObject.toJSONString(bodyMap);

        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        code = getCode(result);
        String attrTypeName = "属性类型" + code;
        String attrTypeLabel = "TTSSAAADEE";
        String attrTypeCode = code;
        String attrTypeFullCode = "01 0000 0 00 " + code + " 00";
        String remark = "属性类型 " + code + "描述";

        bodyMap.put("attrTypeName", attrTypeName);
        bodyMap.put("attrTypeLabel", attrTypeLabel);
        bodyMap.put("attrTypeCode", attrTypeCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        bodyMap.put("remark", "测试属性类型描述" + remark);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        System.out.println(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrTypeFullCode", attrTypeFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);

//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 改
//            String newAttrTypeName = "";
//            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
//            updateBodyMap.put("id", id);
//            updateBodyMap.put("attrTypeName", newAttrTypeName);
//            updateBodyMap.put("attrTypeLabel", attrTypeLabel);
//            updateBodyMap.put("attrTypeCode", attrTypeCode);
//            updateBodyMap.put("attrTypeFullCode", attrTypeFullCode);
//            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
//            updateBodyMap.put("structureTypeCode", structureTypeCode);
//            updateBodyMap.put("remark", "测试属性类型描述2" + code);
//            body = JSONObject.toJSONString(updateBodyMap);
//            result = postForObject(restTemplate, URI + "update", body);
//            System.out.println(result);//运行方法，这里输出：
//            System.out.println("修改成功：" + id);
//
//            // 删
//            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
//            deleteBodyMap.put("id", id);
//            body = JSONObject.toJSONString(deleteBodyMap);
//            result = postForObject(restTemplate, URI + "delete", body);
//            System.out.println(result);//运行方法，这里输出：
//            System.out.println("删除成功：" + id);
//        }
    }

    @Test
    public void testList_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
    }

//    {"structureTypeName":"B","objectTypeFullCode":"14 0000 0 00 00 00"}

    @Test
    public void testList_03() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;

        // 查 "{\"structureTypeName\":\"B\",\"objectTypeFullCode\":\"14 0000 0 00 00 00\"}"; //
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        String objectTypeFullCode = "17 0000 0 00 00 00";
        String structureTypeName = "B";
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode);
        queryBodyMap.put("structureTypeName", structureTypeName);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_01() {
        String result = "";
        String structureTypeCode = "B";
        Integer commonFlag = 0;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_02() {
        String result = "";
        String structureTypeCode = "B";
        Integer commonFlag = 1;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_03() { // TODO 本用例测试通过
        String result = "";
        Integer commonFlag = 0;
        String structureTypeCode = "C";
        String objectTypeFullCode = "01 0000 0 00 00 00";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_04() { // 应该返回01
        String result = "";
        String structureTypeCode = "C";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

    @Test
    public void testGenCode_05() { // 应该返回01
        String result = "";
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String attrTypeFullCode = "01 0000 B 02 00 00";
        String attrTypeCode = "02";
        String structureTypeCode = "B";
        Integer commonFlag = 1;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("attrTypeCode", attrTypeCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

    @Test
    public void testFindAttrTypeList_01() { // 应该返回01
        String result = "";
        Integer commonFlag = 0;
        String structureTypeCode = "B";
        String objectTypeFullCode = "01 0000 0 00 00 00";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "findAttrTypeList", body);
        System.out.println(result);
    }


}
