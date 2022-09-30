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
        String attrTypeLabel = "TTSSAA";
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
    public void testCR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String code = "FF";
        String objectCode = "01 0002 0 0 00 00";
        String attrCode = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        String structureTypeCode = "B";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("structureTypeCode", structureTypeCode);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        printResult(result);

        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 code
        Integer codeResp = (Integer) map.get("code");
        System.out.println(codeResp);

        //获取 msg
        String msg = (String) map.get("msg");
        System.out.println(msg);

        //获取 data
        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");
        System.out.println(resultMap);
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
        result = postForObject(restTemplate, URI +"list", body);
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
        result = postForObject(restTemplate, URI +"list", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_01() {
        String result = "";
        String structureTypeCode = "B";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        bodyMap.put("structureTypeCode", structureTypeCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_02() { // TODO 本用例测试通过
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
    public void testGenCode_03() { // 应该返回01
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
