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

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {ObjectTypeMetaTest.class}) //测试的class
@ContextConfiguration(classes = ObjectTypeMetaTest.class)
public class ObjectTypeMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/";
    }

    @Test
    public void testCRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        String code = getCode(result);
        String objectTypeName = "测试对象类型" + code;
        String objectTypeLabel = "NEW_OBJECT_TYPEAAEE";
        String objectTypeCode = code;
        String objectTypeFullCode = code + " 0000 0 00 00 00";
        bodyMap.put("objectTypeName", objectTypeName);
        bodyMap.put("objectTypeLabel", objectTypeLabel);
        bodyMap.put("objectTypeCode", objectTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("remark", "测试对象类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode);

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

        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            String newObjectTypeName = "新测试对象类型_U";
            String newObjectTypeLabel = "NEW_OBJECT_TYPE_U";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("objectTypeName", newObjectTypeName);
            updateBodyMap.put("objectTypeLabel", newObjectTypeLabel);
//            updateBodyMap.put("objectTypeCode", code);
            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            updateBodyMap.put("remark", "测试对象类型描述" + code);
            body = JSONObject.toJSONString(updateBodyMap);
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            deleteBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            System.out.println("删除成功：" + id);
        }
    }


    @Test
    public void testCreate_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result;
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        String code = getCode(result);
        String objectTypeName = "新测试对象类型";
        String objectTypeLabel = "NEW_OBJECT_TYPE";
        String objectTypeCode = code;
        String objectTypeFullCode = code + " 0000 0 00 00 00";
        bodyMap.put("objectTypeName", objectTypeName);
        bodyMap.put("objectTypeLabel", objectTypeLabel);
        bodyMap.put("objectTypeCode", objectTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("remark", "测试对象类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);
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
        System.out.println("list: " + result);
    }

    @Test
    public void testRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);

        String code = getCode(result);
        String objectTypeName = "新测试对象类型";
        String objectTypeLabel = "NEW_OBJECT_TYPE";
        String objectTypeCode = code;
        String objectTypeFullCode = code + " 0000 0 0 00 00";
        bodyMap.put("objectTypeName", objectTypeName);
        bodyMap.put("objectTypeLabel", objectTypeLabel);
        bodyMap.put("objectTypeCode", objectTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("remark", "测试对象类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
//        // 增
//        result = postForObject(restTemplate, URI +"add", body);
//        printResult(result);
//
//        // 查
//        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
//        queryBodyMap.put("current", 1);
//        queryBodyMap.put("size", 20);
//        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode);
//
//        body = JSONObject.toJSONString(queryBodyMap);
//        result = postForObject(restTemplate, URI +"list", body);
//        printResult(result);
//
//        Map<String, Object> map = new LinkedHashMap<>(16);
//        ObjectMapper mapper = new ObjectMapper();
//        map = mapper.readValue(result, map.getClass());
//
//        //获取 code
//        Integer codeResp = (Integer) map.get("code");
//        System.out.println(codeResp);
//
//        //获取 msg
//        String msg = (String) map.get("msg");
//        System.out.println(msg);
//
//        //获取 data
//        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");
//        System.out.println(resultMap);

        Integer id = 65;//getId(result);
        if (id != null && id != 0) {
            // 改
            String newObjectTypeName = "新测试对象类型_U";
            String newObjectTypeLabel = "NEW_OBJECT_TYPE_U";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("objectTypeName", newObjectTypeName);
            updateBodyMap.put("objectTypeLabel", newObjectTypeLabel);
//            updateBodyMap.put("objectTypeCode", code);
//            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            updateBodyMap.put("remark", "测试对象类型描述" + code);
            body = JSONObject.toJSONString(updateBodyMap);
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("删除成功：" + id);
        }
    }

    @Test
    public void testListAttrs_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";

        String code = "XX";

        String objectTypeCode = "01";
        String objectTypeFullCode = "01 0000 0 00 00 00";
        String structureTypeCode = "B";
        String attrTypeCode = "01";
        String attrTypeFullCode = "01 0000 0 0 00 00";

        String attrName = "属性66";
        String attrCode = "TTEESS";
        String attrFullCode = "01 0000 B 1 04 00";

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        // 对象类型
//        bodyMap.put("objectTypeCode", objectTypeCode);
//        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
//        // 结构类型
//        bodyMap.put("structureTypeCode", structureTypeCode);
        // 属性类型
//        bodyMap.put("attrTypeCode", attrTypeCode);
//        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
//        // 属性
//        bodyMap.put("attrName", attrName);
//        bodyMap.put("attrCode", attrCode);
        bodyMap.put("attrFullCode", attrFullCode);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查
        result = postForObject(restTemplate, URI + "listAttrs", body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";

        Map<String, Object> bodyMap = new LinkedHashMap<>();

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
        printResult(result);
    }

    //
    @Test
    public void testGenCode_02() throws Exception {
        String lastCode = "33";
        List<String> list = Arrays.asList(lastCode.split(""));

        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    @Test
    public void testGenCode_03() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }

    @Test
    public void testDeleteInBatches_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        Integer[] ids = new Integer[2];
        String[] objectTypeFullCodes = new String[2];
        String code = getCode(result);
        String objectTypeName = "新测试对象类型" + code;
        String objectTypeLabel = "CCC_OBJECT_TYPE_A";
        String objectTypeCode = code;
        String objectTypeFullCode1 = code + " 0000 0 0 00 00";
        objectTypeFullCodes[0] = objectTypeFullCode1;
        bodyMap.put("objectTypeName", objectTypeName);
        bodyMap.put("objectTypeLabel", objectTypeLabel);
        bodyMap.put("objectTypeCode", objectTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode1);
        bodyMap.put("remark", "测试对象类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);

        // 再次获取新code
        result = postForObject(restTemplate, URI + "genCode", body);
        code = getCode(result);
        objectTypeName = "新测试对象类型" + code;
        String objectTypeLabel2 = "CCC_OBJECT_TYPE_B";
        objectTypeCode = code;
        String objectTypeFullCode2 = code + " 0000 0 0 00 00";
        objectTypeFullCodes[1] = objectTypeFullCode2;
        bodyMap.put("objectTypeName", objectTypeName);
        bodyMap.put("objectTypeLabel", objectTypeLabel2);
        bodyMap.put("objectTypeCode", objectTypeCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode2);
        bodyMap.put("remark", "测试对象类型描述" + code);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);

        // 增
        result = postForObject(restTemplate, URI + "add", body);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode1);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI +"list", body);
        ids[0] = getId(result);

        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode2);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI +"list", body);
        ids[1] = getId(result);

        Map<String, Object> tempMap1 = new LinkedHashMap<>();
        Map<String, Object> tempMap2 = new LinkedHashMap<>();
        List<Map<String, Object>> deleteList = new ArrayList<>();
        if (ids[0] != null && ids[1] != null && ids[0] != 0 && ids[1] != 0) {
            tempMap1.put("id", ids[0]);
            tempMap1.put("objectTypeFullCode", objectTypeFullCodes[0]);
            deleteList.add(tempMap1);
            tempMap2.put("id", ids[1]);
            tempMap2.put("objectTypeFullCode", objectTypeFullCodes[1]);
            deleteList.add(tempMap2);

            body = JSONObject.toJSONString(deleteList);
            System.out.println(body);
            result = postForObject(restTemplate, URI + "deleteInBatches", body);
            System.out.println(result);//运行方法，这里输出：
        }
    }


}
