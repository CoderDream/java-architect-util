package com.example.demo.codesystem;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {AttrItemMetaTest.class}) //测试的class
@ContextConfiguration(classes = AttrItemMetaTest.class)
public class AttrItemMetaTest extends BaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/";
    }

    @Test
    public void testDeleteBatchIds_01() throws Exception {
        final String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/deleteBatchIds";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> idMap = new LinkedHashMap<>();
        idMap.put("id", 18);
        Object[] objects = new Object[1];
        objects[0] = idMap;
        String body = JSONObject.toJSONString(objects);
        System.out.println("body: " + body);

        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 code
        Integer code = (Integer) map.get("code");
        System.out.println(code);

        //获取 msg
        String msg = (String) map.get("msg");
        System.out.println(msg);

        //获取 data
        Map<String, Object> dataMap = (LinkedHashMap) map.get("data");
        System.out.println(dataMap); // TODO
    }

    @Test
    public void testCRUD_01() throws Exception {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrFullCode = "01 0000 B 1 05 00";
        String objectFullCode = "01 0002 0 00 00 00";
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        String code = getCode(result);
        System.out.println(code);

        String attrItemName = "属性条目" + code;
        String attrItemLabel = "TEST_ITEMA";
        String attrItemCode = code;
        String attrItemFullCode = "01 0002 0 0 " + code + " 00";
        String remark = "属性条目 " + code + "描述";
        String hashRateLevel = "collection";
        String dataSource = "model";

        bodyMap.put("attrItemName", attrItemName);
        bodyMap.put("attrItemLabel", attrItemLabel);
        bodyMap.put("attrItemCode", attrItemCode);
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("remark", "测试属性类型描述" + remark);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrItemFullCode", attrItemFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);
        System.out.println(result);

        Integer id = getId(result);
        if (id != null && id != 0) {
            // 改
            String newObjectCode = "01 0002 0 0 00 00";
            String newAttrCode = "01 [MMMM] B 1 01 00";
            String newAttrFullCode = "01 [MMMM] B 1 01 00";
            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
            updateBodyMap.put("id", id);
            updateBodyMap.put("attrItemName", attrItemName);
            updateBodyMap.put("attrItemLabel", attrItemLabel);
            updateBodyMap.put("attrItemCode", attrItemCode);
            updateBodyMap.put("attrItemFullCode", attrItemFullCode);
//            updateBodyMap.put("objectFullCode", objectFullCode);
//            updateBodyMap.put("attrFullCode", newAttrFullCode);
            updateBodyMap.put("remark", "测试属性类型描述New" + remark);
            updateBodyMap.put("hashRateLevel", hashRateLevel);
            updateBodyMap.put("dataSource", dataSource);
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
    public void testCR_01() throws Exception {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrFullCode = "01 0000 B 01 07 00";
        String objectFullCode = "01 0002 0 00 00 00";
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        String code = getCode(result);
        System.out.println(code);

        String attrItemName = "属性条目" + code;
        String attrItemLabel = "TEST_ITEM00";
        String attrItemCode = code;
        String attrItemFullCode = "01 0002 0 0 " + code + " 00";
        String remark = "属性条目 " + code + "描述";
        String hashRateLevel = "collection";
        String dataSource = "model";

        bodyMap.put("attrItemName", attrItemName);
        bodyMap.put("attrItemLabel", attrItemLabel);
        bodyMap.put("attrItemCode", attrItemCode);
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("remark", "测试属性类型描述" + remark);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        result = postForObject(restTemplate, URI + "add", body);
        printResult(result);

        // 查
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("attrItemFullCode", attrItemFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);

        System.out.println(result);

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
//
//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 改
//            String newObjectCode = "01 0002 0 0 00 00";
//            String newAttrCode = "01 [MMMM] B 1 01 00";
//            String newAttrFullCode = "01 [MMMM] B 1 01 00";
//            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
//            updateBodyMap.put("id", id);
//            updateBodyMap.put("attrItemName", attrItemName);
//            updateBodyMap.put("attrItemLabel", attrItemLabel);
//            updateBodyMap.put("attrItemCode", attrItemCode);
//            updateBodyMap.put("attrItemFullCode", attrItemFullCode);
////            updateBodyMap.put("objectFullCode", objectFullCode);
////            updateBodyMap.put("attrFullCode", newAttrFullCode);
//            updateBodyMap.put("remark", "测试属性类型描述New" + remark);
//            updateBodyMap.put("hashRateLevel", hashRateLevel);
//            updateBodyMap.put("dataSource", dataSource);
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
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrFullCode = "01 0000 B 01 07 00";
        String objectFullCode = "01 0002 0 00 00 00";
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        String code = getCode(result);
        System.out.println(code);

        String attrItemName = "属性条目" + code;
        String attrItemLabel = "TEST_ITEM00";
        String attrItemCode = code;
        String attrItemFullCode = "01 0002 0 0 " + code + " 00";
        String remark = "属性条目 " + code + "描述";
        String hashRateLevel = "collection";
        String dataSource = "model";

        bodyMap.put("attrItemName", attrItemName);
        bodyMap.put("attrItemLabel", attrItemLabel);
        bodyMap.put("attrItemCode", attrItemCode);
        bodyMap.put("attrItemFullCode", attrItemFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("remark", "测试属性类型描述" + remark);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);

        body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
//        result = postForObject(restTemplate, URI + "add", body);
//        printResult(result);

        // 查
        String objectTypeFullCode = "14 0000 0 00 00 00";
        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("objectTypeFullCode", objectTypeFullCode);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, URI + "list", body);

        System.out.println("list: " + result);

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
//
//        Integer id = getId(result);
//        if (id != null && id != 0) {
//            // 改
//            String newObjectCode = "01 0002 0 0 00 00";
//            String newAttrCode = "01 [MMMM] B 1 01 00";
//            String newAttrFullCode = "01 [MMMM] B 1 01 00";
//            Map<String, Object> updateBodyMap = new LinkedHashMap<>();
//            updateBodyMap.put("id", id);
//            updateBodyMap.put("attrItemName", attrItemName);
//            updateBodyMap.put("attrItemLabel", attrItemLabel);
//            updateBodyMap.put("attrItemCode", attrItemCode);
//            updateBodyMap.put("attrItemFullCode", attrItemFullCode);
////            updateBodyMap.put("objectFullCode", objectFullCode);
////            updateBodyMap.put("attrFullCode", newAttrFullCode);
//            updateBodyMap.put("remark", "测试属性类型描述New" + remark);
//            updateBodyMap.put("hashRateLevel", hashRateLevel);
//            updateBodyMap.put("dataSource", dataSource);
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
    public void testList_02() throws Exception {
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

    @Test
    public void testGenCode_03() {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrFullCode = "01 0000 B 01 07 00";
        String objectFullCode = "01 0002 0 0 00 00";
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        result = postForObject(restTemplate, URI + "genCode", body);
        System.out.println(getCode(result));
    }
}
