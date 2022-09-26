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
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {AttrMetaTest.class}) //测试的class
@ContextConfiguration(classes = AttrMetaTest.class)
public class AttrMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-meta/";
    }

    @Test
    public void testCRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String attrTypeFullCode = "01 0000 B 02 00 00";
        Integer commonFlag = 0;
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("commonFlag", commonFlag);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        String code = getCode(result);
        System.out.println(code);
        if(code == null || "".equals(code.trim())) {
            System.out.println("######" + getMessage(result));
        }

        String attrName = "属性66" + code;
        String attrLabel = "TTEESSTTbbaddddd";
        String attrCode = code;
        String attrFullCode = "01 0000 B 02 " + code + " 00";
        //String attrTypeFullCode = objectTypeCode + "0000 0 " + structureTypeCode + " " + temp + " 00";
        String remark = "属性类型 " + code + "描述";
        bodyMap.put("attrName", attrName);
        bodyMap.put("attrLabel", attrLabel);
        bodyMap.put("attrCode", attrCode);
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("commonFlag", commonFlag);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
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
        queryBodyMap.put("attrFullCode", attrFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println("list:  " + result);

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

        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            //String newAttrTypeCode = "B4";
            String newAttrName = "B4";
//            String newAttrTypeFullCode = "01 0000 B 03 00 00";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("attrName", newAttrName);
            updateBodyMap.put("attrLabel", attrLabel);
            updateBodyMap.put("attrCode", attrCode);
            updateBodyMap.put("attrFullCode", attrFullCode);
            updateBodyMap.put("commonFlag", commonFlag);
            updateBodyMap.put("attrTypeFullCode", attrTypeFullCode);
            updateBodyMap.put("remark", "测试属性描述new" + remark);
           // updateBodyMap.put("attrTypeCode", newAttrTypeCode);
            body = JSONObject.toJSONString(updateBodyMap);
            System.out.println("updateBodyMap" + body);//运行方法，这里输出：
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            deleteBodyMap.put("attrFullCode", attrFullCode);
            body = JSONObject.toJSONString(deleteBodyMap);
            System.out.println("deleteBodyMap" + body);//运行方法，这里输出：
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("删除成功：" + id);
        }
    }

    @Test
    public void testCRUD_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String temp = "35";
        String structureTypeCode = "B";
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String attrTypeFullCode = "01 0000 B 35 00 00";
        String attrName = "测试属性35";
        String attrLabel = "cssx";
        String attrCode = temp;
        String attrFullCode = "01 0000 B 35 35 00";
        Integer commonFlag = 0;
        String remark = "属性类型 " + temp + "描述";

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("attrName", attrName);
        bodyMap.put("attrLabel", attrLabel);
        bodyMap.put("attrCode", attrCode);
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("commonFlag", commonFlag);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("remark", "测试属性类型描述" + remark);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrFullCode", attrFullCode);

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

//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 改
//            //String newAttrTypeCode = "B4";
//            String newAttrName = "B4";
//            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
//            updateBodyMap.put("id", id);
//            updateBodyMap.put("attrName", newAttrName);
//            updateBodyMap.put("attrLabel", attrLabel);
//            updateBodyMap.put("attrCode", attrCode);
//            updateBodyMap.put("attrFullCode", attrFullCode);
//            updateBodyMap.put("commonFlag", commonFlag);
//            updateBodyMap.put("attrTypeFullCode", attrTypeFullCode);
//            updateBodyMap.put("structureTypeCode", structureTypeCode);
//            updateBodyMap.put("remark", "测试属性描述new" + remark);
//            // updateBodyMap.put("attrTypeCode", newAttrTypeCode);
//            body = JSONObject.toJSONString(updateBodyMap);
//            result = postForObject(restTemplate, URI + "update", body);
//            System.out.println(result);//运行方法，这里输出：
//            printResult(result);
//            System.out.println("修改成功：" + id);
//
//            // 删
//            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
//            deleteBodyMap.put("id", id);
//            body = JSONObject.toJSONString(deleteBodyMap);
//            result = postForObject(restTemplate, URI + "delete", body);
//            System.out.println(result);//运行方法，这里输出：
//            printResult(result);
//            System.out.println("删除成功：" + id);
//        }
    }

    @Test
    public void testList_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);

        String structureTypeCode = "B";
        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
//        queryBodyMap.put("attrFullCode", "B 2 11");
        queryBodyMap.put("structureTypeCode", structureTypeCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println("body: " + result);
    }

    /**
     * 基础属性
     *
     * @throws Exception
     */
    @Test
    public void testReadAll_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String structureTypeCode = "B";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("structureTypeCode", structureTypeCode);
//
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAll", body);
        System.out.println(result);
    }

    /**
     * 数据属性
     *
     * @throws Exception
     */
    @Test
    public void testReadAll_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String structureTypeCode = "D";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("structureTypeCode", structureTypeCode);
//
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAll", body);
        System.out.println(result);
    }

    /**
     * 获取属性元数据三层树，用于创建属性条目
     *
     * @throws Exception
     */
    @Test
    public void testListAllStructureAttrs_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String structureTypeCode = "D";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
//        bodyMap.put("structureTypeCode", structureTypeCode);
//
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAllStructureAttrs", body);
        System.out.println(result);
    }

    @Test
    public void testR_0101() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        String body = "";
        String code = "FF";
        String objectCode = "01 0002 0 0 00";
        String hashRateLevel = "collection";
        String dataSource = "model";
        String attrTypeCode = "D1";
        String structureTypeCode = "D";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("attrTypeCode", attrTypeCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
//        queryBodyMap.put("code", code);
        queryBodyMap.put("attrTypeCode", attrTypeCode);
        queryBodyMap.put("structureTypeCode", structureTypeCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
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

//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 删
//            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
//            deleteBodyMap.put("id", id);
//            body = JSONObject.toJSONString(deleteBodyMap);
//            result = postForObject(restTemplate, URI + "delete", body);
//            System.out.println(result);//运行方法，这里输出：
//            printResult(result);
//            System.out.println("删除成功：" + id);
//        }
    }

    @Test
    public void testC_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String code = "34";
        String label = "xz1";
        String name = "测试新增属性1";
        String objectCode = "01 0002 0 0 00 00";
        String attrTypeCode = "99";
        String hashRateLevel = "collection";
        String dataSource = "model";
        String structureTypeCode = "B";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("label", label);
        bodyMap.put("code", code);
        bodyMap.put("attrTypeCode", attrTypeCode);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
//        bodyMap.put("remark", "测试类型描述" + code);

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

//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 删
//            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
//            deleteBodyMap.put("id", id);
//            body = JSONObject.toJSONString(deleteBodyMap);
//            result = postForObject(restTemplate, URI + "delete", body);
//            System.out.println(result);//运行方法，这里输出：
//            printResult(result);
//            System.out.println("删除成功：" + id);
//        }
    }

    @Test
    public void testGenCode_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String structureTypeCode = "B";
        String attrTypeFullCode = "01 0000 B 04 00 00";
        bodyMap.put("structureTypeCode", structureTypeCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

    //{"objectTypeFullCode":"01 0000 0 00 00 00","attrTypeFullCode":"01 0000 B 01 00 00","structureTypeCode":"B"}

    @Test
    public void testGenCode_02() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrTypeFullCode = "01 0000 B 04 00 00";
        Integer commonFlag = 0;
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

    @Test
    public void testGenCode_03() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrTypeFullCode = "01 0000 B 02 00 00";
        Integer commonFlag = 0;
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("commonFlag", commonFlag);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

}
