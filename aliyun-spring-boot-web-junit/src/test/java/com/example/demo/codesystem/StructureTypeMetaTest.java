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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {StructureTypeMetaTest.class}) //测试的class
@ContextConfiguration(classes = StructureTypeMetaTest.class)
public class StructureTypeMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/structure-type-meta/";
    }

    @Test
    public void testCRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String code = "FF";
        String objectCode = "01 0002 0 0 00 00";
        String attrCode = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        String attrStructTypeCode = "B";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("attrStructTypeCode", attrStructTypeCode);
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
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        printResult(result);

        Map<String, Object> map = new HashMap<>(16);
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
            // 删
            Map<String, Object> deleteBodyMap = new HashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
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
        String attrStructTypeCode = "B";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("attrStructTypeCode", attrStructTypeCode);
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
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        printResult(result);

        Map<String, Object> map = new HashMap<>(16);
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
    public void testR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String code = "FF";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("code", code);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = "";
//        String result = postForObject(restTemplate, URI + "add", body);
//        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        printResult(result);

        Map<String, Object> map = new HashMap<>(16);
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

}
