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

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {AttrItemMetaTest.class}) //测试的class
@ContextConfiguration(classes = AttrItemMetaTest.class)
public class AttrItemMetaTest extends BaseTest {

    @Value("${env}")
    private String env;

    private String URI_PREFIX;

    /**
     * admin Service
     */
    private String ADMIN_PORT;

    private String BUS_PORT;

    /**
     * Data Service Port
     */
    private String DS_PORT;

    /**
     * Business Service Port
     */
    private String BS_PORT;

    @Before
    public void init() throws JsonProcessingException {
        switch (env) {
            case "local":
                URI_PREFIX = "http://192.168.18.75:";
                ADMIN_PORT = "9085";
                BUS_PORT = "9086";
                DS_PORT = "7080";
                BS_PORT = "9085";
                break;
            case "sit":
                URI_PREFIX = "http://172.16.104.61:";
                ADMIN_PORT = "30572";
                BUS_PORT = "30965";
                DS_PORT = "7080";
                break;
            case "so-sit":
                URI_PREFIX = "http://172.16.104.131:";
                ADMIN_PORT = "30572";
                BUS_PORT = "32355";
                DS_PORT = "7080";
                BS_PORT = "32355";
                break;
            case "nas":
                URI_PREFIX = "http://27.19.125.63:";
                ADMIN_PORT = "30572";
                BUS_PORT = "30965";
                DS_PORT = "7080";
                break;
        }

//        final String uri = URI_PREFIX + ADMIN_PORT + "/api/data/sys/login";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"password\":\"c0e0857806d4fb4769bd4d9f501dbe6d\",\"username\":\"xulin\",\"code\":\"4\"}";//实例请求参数
//        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
//        //设置contentType 防止中文乱码
//        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
//        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
//        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
//        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
//        HttpEntity<String> strEntity = new HttpEntity<String>(user, headers);
//        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
//        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
//        String result = restTemplate.postForObject(uri, strEntity, String.class);
//        // System.out.println(result);//运行方法，这里输出：
//
//        Map<String, Object> map = new HashMap<>(16);
//        ObjectMapper mapper = new ObjectMapper();
//        map = mapper.readValue(result, map.getClass());
//
//        //获取 code
//        Integer code = (Integer) map.get("code");
//        System.out.println(code);
//
//        //获取 msg
//        String msg = (String) map.get("msg");
//        System.out.println(msg);
//
//        //获取 data
//        Map<String, Object> dataMap = (LinkedHashMap) map.get("data");

        //获取 captchaOnOff
        //  List roles = (List) dataMap.get("roles");
        // System.out.println(roles.get(0));

    }


    @Test
    public void testListAll_01() throws Exception {
        final String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";
        RestTemplate restTemplate = new RestTemplate();

        String body = "{\"page\":1,\"rows\":20,\"query\":{}}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        Map<String, Object> map = new HashMap<>(16);
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
    public void testAdd_01() throws Exception {
        final String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/add";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        Random r = new Random();
        int randomNumber = r.nextInt(9999) + 1000; // 生成[-3,15)区间的整数
        String tempString = "temp" + randomNumber;
        bodyMap.put("name", tempString);
        bodyMap.put("label", tempString);
        bodyMap.put("code", tempString);
        bodyMap.put("objectCode", "02");
        bodyMap.put("attrCode", "02");
        bodyMap.put("code", tempString);
        bodyMap.put("dataSource", 1);
        bodyMap.put("hashRateLevel", "1");
        bodyMap.put("remark", tempString);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);

        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        Map<String, Object> map = new HashMap<>(16);
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
    public void testListQuery_04() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";

        String dateString = "15";
        Map<String, Object> bodyMap = new HashMap<String, Object>();
//        bodyMap.put("page", 1);
//        bodyMap.put("rows", 20);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //  queryMap.put("code", dateString);
//        bodyMap.put("query", queryMap);

        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        bodyMap.put("name", "888");

        String body = JSONObject.toJSONString(bodyMap);

        // String body = "{\"name\":\"测试15\",\"label\":\"测试15\",\"code\":\"15\",\"remark\":\"测试类型描述\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        printResult(result);
    }

    @Test
    public void testListQuery_05() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";

        String dateString = "15";
        Map<String, Object> bodyMap = new HashMap<String, Object>();
//        bodyMap.put("page", 1);
//        bodyMap.put("rows", 20);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //  queryMap.put("code", dateString);
//        bodyMap.put("query", queryMap);
        String[] objectCodes = new String[1];
        objectCodes[0] = "02";
        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        bodyMap.put("name", "三峡水库参考平滑入库流量");

        String body = JSONObject.toJSONString(bodyMap);

        // String body = "{\"name\":\"测试15\",\"label\":\"测试15\",\"code\":\"15\",\"remark\":\"测试类型描述\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        printResult(result);
    }

    @Test
    public void testListQuery_06() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        String[] attrCodes = new String[1];
        attrCodes[0] = "02";
        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        bodyMap.put("attrCodes", attrCodes);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        printResult(result);
    }

    @Test
    public void testListQuery_07() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        String[] attrCodes = new String[1];
        attrCodes[0] = "02";
        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        bodyMap.put("attrType", "B");

        String body = JSONObject.toJSONString(bodyMap);

        // String body = "{\"name\":\"测试15\",\"label\":\"测试15\",\"code\":\"15\",\"remark\":\"测试类型描述\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        printResult(result);
    }

    @Test
    public void testListQuery_08() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        String[] attrCodes = new String[1];
        attrCodes[0] = "02";
        bodyMap.put("current", 1);
        bodyMap.put("size", 20);
        bodyMap.put("hashRateLevel", "collection");
        bodyMap.put("dataSource", "model");

        String body = JSONObject.toJSONString(bodyMap);

        // String body = "{\"name\":\"测试15\",\"label\":\"测试15\",\"code\":\"15\",\"remark\":\"测试类型描述\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        printResult(result);
    }

    @Test
    public void testDeleteBatchIds_01() throws Exception {
        final String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/deleteBatchIds";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> idMap = new HashMap<>();
        idMap.put("id", 18);
        Object[] objects = new Object[1];
        objects[0] = idMap;
        String body = JSONObject.toJSONString(objects);
        System.out.println("body: " + body);

        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        String result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：

        Map<String, Object> map = new HashMap<>(16);
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
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/add";

        String code = "CC";
        String objectCode = "01 0002 0 0 00 00";
        String attrType = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("attrType", attrType);
        bodyMap.put("objectCode", objectCode);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, uri, body);
        printResult(result);

        // 查
        uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, uri, body);
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
            uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/delete";
            Map<String, Object> deleteBodyMap = new HashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, uri, body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("删除成功：" + id);
        }
    }

    @Test
    public void testCR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/add";

        String code = "CC";
        String objectCode = "01 0002 0 0 00 00";
        String attrCode = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("objectCode", objectCode);
        bodyMap.put("attrCode", attrCode);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, uri, body);
        printResult(result);

        // 查
        uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, uri, body);
        printResult(result);
    }

    @Test
    public void testR_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/add";

        String code = "CC";
        String objectCode = "01 0002 0 0 00 00";
        String attrCode = "01 [MMMM] B 1 01 01";
        String hashRateLevel = "collection";
        String dataSource = "model";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("attrCode", attrCode);
        bodyMap.put("objectCode", objectCode);
        bodyMap.put("hashRateLevel", hashRateLevel);
        bodyMap.put("dataSource", dataSource);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = null;
//        result = postForObject(restTemplate, uri, body);
//        printResult(result);

        // 查
        uri = URI_PREFIX + BS_PORT + "/api/standardized/object/attr-item-meta/list";
        Map<String, Object> queryBodyMap = new HashMap<>();
        queryBodyMap.put("current", 1);
        queryBodyMap.put("size", 20);
        queryBodyMap.put("code", code);

        body = JSONObject.toJSONString(queryBodyMap);
        result = postForObject(restTemplate, uri, body);
        System.out.println(JSONObject.toJSONString(result));
        printResult(result);
    }
}
