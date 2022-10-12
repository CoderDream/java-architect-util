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
@SpringBootTest(classes = {ObjectMetaTest.class}) //测试的class
@ContextConfiguration(classes = ObjectMetaTest.class)
public class ObjectMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/object-meta/";
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

        String spaceLevel = "L1";
        String objectCode = code;
        String objectName = "对象" + code;
        String objectLabel = "DDRRTT";
        String objectFullCode = "03 0000 0 " + code + " 00 00";
        String objectTypeFullCode = "03 0000 0 00 00 00";

        bodyMap.put("objectName", objectName);
        bodyMap.put("objectLabel", objectLabel);
        bodyMap.put("objectCode", objectCode);
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("remark", "测试类型描述" + code);
        bodyMap.put("spaceLevel", spaceLevel);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        System.out.println(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectFullCode", objectFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);


        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            String newObjectName = "01 0002 0 0 02 00";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("objectName", newObjectName);
            updateBodyMap.put("objectLabel", objectLabel);
            updateBodyMap.put("objectTCode", objectCode);
            updateBodyMap.put("objectFullCode", objectFullCode);
            updateBodyMap.put("objectTypeFullCode", objectTypeFullCode);
            updateBodyMap.put("remark", "new测试类型描述" + code);
            updateBodyMap.put("spaceLevel", spaceLevel);
            body = JSONObject.toJSONString(updateBodyMap);
            result = postForObject(restTemplate, URI + "update", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("修改成功：" + id);

            // 删
            Map<String, Object> deleteBodyMap = new LinkedHashMap<>();
            deleteBodyMap.put("id", id);
            deleteBodyMap.put("objectFullCode", objectFullCode);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, URI + "delete", body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
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

        String spaceLevel = "L1";
        String objectCode = code;
        String objectName = "测试对象" + code;
        String objectLabel = "DDRR";
        String objectFullCode = "15 " + code + " 0 00 00 00";
        String objectTypeFullCode = "15 0000 0 00 00 00";
        bodyMap.put("objectName", objectName);
        bodyMap.put("objectLabel", objectLabel);
        bodyMap.put("objectCode", objectCode);
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        bodyMap.put("remark", "测试类型描述" + code);
        bodyMap.put("spaceLevel", spaceLevel);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectFullCode", objectFullCode);

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
    public void testUpdate_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
//        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String body;

//                String code = "DD";
//        String spaceLevel = "L1";
//        String objectCode = "01 0002 0 0 00 00";
//        String objectName = "01 0002 0 0 00 00";
//        String objectLabel = "DDRR";
//        String objectFullCode = "01 0002 0 0 00 00";
//        String objectTypeFullCode = "01 0000 0 0 00 00";
//
//        bodyMap.put("objectName", objectName);
//        bodyMap.put("objectLabel", objectLabel);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("objectFullCode", objectFullCode);
//        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
//        bodyMap.put("remark", "测试类型描述" + code);
//        bodyMap.put("spaceLevel", spaceLevel);
//
//        String body = JSONObject.toJSONString(bodyMap);
//        System.out.println("body: " + body);
//        // 增
//        String result = postForObject(restTemplate, URI + "add", body);
//        printResult(result);
//
//        // 查
//        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
//        queryBodyMap.put("current", 1);
//        queryBodyMap.put("size", 20);
//        queryBodyMap.put("objectFullCode", objectFullCode);
//
//        body = JSONObject.toJSONString(queryBodyMap);
//        result = postForObject(restTemplate, URI + "list", body);
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

        Integer id = 18; //getId(result);
        // 改
        String newObjectCode = "1112";
        String newObjectFullCode = "01 1112 0 00 00 00";
        String newObjectLabel = "cs";
        String newObjectName = "测试";
        String newObjectTypeFullCode = "01 0000 0 00 00 00";
        String newRemark = "cs";
        String newSpaceLevel = "L1";

        Map<String, Object> updateBodyMap = new LinkedHashMap<>();
        updateBodyMap.put("id", id);
        updateBodyMap.put("objectName", newObjectName);
        updateBodyMap.put("objectLabel", newObjectLabel);
        updateBodyMap.put("objectCode", newObjectCode);
        updateBodyMap.put("objectFullCode", newObjectFullCode);
        updateBodyMap.put("objectTypeFullCode", newObjectTypeFullCode);
        updateBodyMap.put("remark", newRemark);
        updateBodyMap.put("spaceLevel", newSpaceLevel);
        body = JSONObject.toJSONString(updateBodyMap);
        result = postForObject(restTemplate, URI + "update", body);
        System.out.println(result);//运行方法，这里输出：
        printResult(result);
        System.out.println("修改成功：" + id);
    }

    @Test
    public void testUpdate_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        String body = "{\"id\":167,\"objectName\":\"罗江以上\",\"objectLabel\":\"Luojiang_above\",\"objectCode\":\"0012\",\"objectFullCode\":\"1E 0012 0 00 00 00\",\"updateTime\":\"2022-10-10 14:32:39\",\"createTime\":\"2022-10-10 14:17:28\",\"remark\":\"106133\",\"objectTypeCode\":\"1E\",\"objectTypeFullCode\":\"1E 0000 0 00 00 00\",\"objectTypeName\":\"流域3级\",\"spaceLevel\":\"L1\",\"code1\":\"0\",\"code2\":\"0\",\"code3\":\"1\",\"code4\":\"1\"}";
        result = postForObject(restTemplate, URI + "update", body);
        System.out.println(result);//运行方法，这里输出：
        printResult(result);
        System.out.println("修改成功：");
    }

    @Test
    public void testR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAll", body);
        System.out.println(result);
    }

    @Test
    public void testListAttrItemInfo_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String objectFullCode = "01 0002 0 00 00 00";
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAttrItemInfo", body);
        System.out.println(result);
    }

    /**
     * INSERT INTO `object_data`.`object_attr_item_index`
     * (`id`, `object_full_code`, `attr_item_full_code`, `data_type`)
     * VALUES (11, '01 0002 0 00 00 00', '01 0002 B 02 2Y 01', 'string');
     */
    @Test
    public void testListAttrItemInfo_02() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String objectFullCode = "01 0002 0 00 00 00";// 对象全码
        String attrTypeFullCode = "01 0000 B 02 00 00";// 属性类型全码
        String structureTypeCode = "B"; // 结构类型
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        String body = JSONObject.toJSONString(bodyMap);
//        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAttrItemInfo", body);
        System.out.println(result);
    }

    @Test
    public void testListAttrItemInfo_03() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String objectFullCode = "01 0002 0 00 00 00";// 对象全码
        String attrTypeFullCode = "01 0000 B 04 00 00";// 属性类型全码
        String structureTypeCode = "B"; // 结构类型
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAttrItemInfo", body);
//        System.out.println(result);
    }


    @Test
    public void testQueryAttrItemInfo_01() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String objectFullCode = "01 0002 0 00 00 00";// 对象全码
        String attrItemFullCode = "01 0002 B 04 01 01";// 属性条目全码
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        //System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "queryAttrItemInfo", body);
        System.out.println(result);
    }



    //
    @Test
    public void testListAttrItemTree_01() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        String objectFullCode = "01 0002 0 00 00 00";// 对象全码
        String attrTypeFullCode = "01 0000 B 02 00 00";// 属性类型全码
        String structureTypeCode = "B"; // 结构类型
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrTypeFullCode", attrTypeFullCode);
        bodyMap.put("structureTypeCode", structureTypeCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 查询树
        String result = postForObject(restTemplate, URI + "listAttrItemTree", body);
        System.out.println(result);
    }

    @Test
    public void testAR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String code = "DD";
        String objectCode = "01 0002 0 0 00 00";
        String attrType = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
//        bodyMap.put("attrType", attrType);
//        bodyMap.put("objectCode", objectCode);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, URI + "addRelation", body);
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

        Integer id = getId(result);
        if (id != null && id != 0) {
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

    /**
     * 获取对象元数据树
     *
     * @throws Exception
     */
    @Test
    public void testListAttrs_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/listAttrs";
        Integer current = 1;
        Integer size = 20;
        String structureTypeCode = "B";
        String objectTypeCode = "01";
        String attrTypeCode = "B1";
        String attrName = "水库代码";
        String attrCode = "01 [MMMM] B 1 01 00";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("current", current); // 当前页
        bodyMap.put("size", size); // 页面大小
        bodyMap.put("objectTypeCode", objectTypeCode);  // 对象类型-水库
        bodyMap.put("structureTypeCode", structureTypeCode); // 结构类型-基础属性
        bodyMap.put("attrTypeCode", attrTypeCode); // 属性类型-标识信息
        bodyMap.put("attrName", attrName); // 属性名称
        bodyMap.put("attrCode", attrCode); // 属性编码
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        String result = postForObject(restTemplate, uri, body);
        System.out.println(result);
    }

    @Test
    public void testListAttrs_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/listAttrs";
        Integer current = 1;
        Integer size = 20;
        String structureTypeCode = "B";
        String objectTypeCode = "01";
        String objectTypeFullCode = "01 0000 0 00 00 00";
//        String attrTypeCode = "B1";
//        String attrName = "水库代码";
//        String attrCode = "01 [MMMM] B 1 01 00";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("current", current); // 当前页
        bodyMap.put("size", size); // 页面大小
//        bodyMap.put("objectTypeCode", objectTypeCode);  // 对象类型-水库
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);  // 对象类型-水库
//        bodyMap.put("structureTypeCode", structureTypeCode); // 结构类型-基础属性
//        bodyMap.put("attrTypeCode", attrTypeCode); // 属性类型-标识信息
//        bodyMap.put("attrName", attrName); // 属性名称
//        bodyMap.put("attrCode", attrCode); // 属性编码
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        String result = postForObject(restTemplate, uri, body);
        System.out.println(result);
    }

    @Test
    public void testGenCode_01() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String objectTypeFullCode = "01 0000 0 0 00 00";
        bodyMap.put("objectTypeFullCode", objectTypeFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(result);
    }

}
