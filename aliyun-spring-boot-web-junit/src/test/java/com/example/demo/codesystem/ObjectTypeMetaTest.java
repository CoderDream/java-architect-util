package com.example.demo.codesystem;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
@SpringBootTest(classes = {ObjectTypeMetaTest.class}) //测试的class
@ContextConfiguration(classes = ObjectTypeMetaTest.class)
public class ObjectTypeMetaTest extends BaseTest {

//    @Value("${env}")
//    private String env;
//
//    private String URI_PREFIX;
//
//    /**
//     * admin Service
//     */
//    private String ADMIN_PORT;
//
//    private String BUS_PORT;
//
//    /**
//     * Data Service Port
//     */
//    private String DS_PORT;
//
//
//    /**
//     * Business Service Port
//     */
//    private String BS_PORT;

    @Before
    public void init() throws JsonProcessingException {
//        switch (env) {
//            case "local":
//                URI_PREFIX = "http://192.168.18.75:";
//                ADMIN_PORT = "9085";
//                BUS_PORT = "9086";
//                DS_PORT = "7080";
//                BS_PORT = "9085";
//                break;
//            case "local_home":
//                URI_PREFIX = "http://192.168.3.85:";
//                ADMIN_PORT = "9085";
//                BUS_PORT = "9086";
//                DS_PORT = "7080";
//                BS_PORT = "9085";
//                break;
//            case "sit":
//                URI_PREFIX = "http://172.16.104.61:";
//                ADMIN_PORT = "30572";
//                BUS_PORT = "30965";
//                DS_PORT = "7080";
//                break;
//            case "so-sit":
//                URI_PREFIX = "http://172.16.104.131:";
//                ADMIN_PORT = "30572";
//                BUS_PORT = "32355";
//                DS_PORT = "7080";
//                BS_PORT = "32355";
//                break;
//            case "nas":
//                URI_PREFIX = "http://27.19.125.63:";
//                ADMIN_PORT = "30572";
//                BUS_PORT = "30965";
//                DS_PORT = "7080";
//                break;
//        }
        super.init();
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
    public void testListQuery_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/list";

        String dateString = "15";
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("page", 1);
        bodyMap.put("rows", 20);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("code", dateString);
        bodyMap.put("query", queryMap);

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
//
//    private static void printResult(String result) throws JsonProcessingException {
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
//        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");
//        System.out.println(resultMap);
//
//        if (resultMap != null) {
//            //获取 data
//            List<Map<String, Object>> records = (ArrayList) resultMap.get("records");
//            System.out.println(records);
//
//            for (Map<String, Object> recordMap : records) {
//                System.out.println((Integer) recordMap.get("id"));
//            }
//        }
//    }
//
//    private static String getMessage(String result) throws JsonProcessingException {
//        Map<String, Object> map = new HashMap<>(16);
//        ObjectMapper mapper = new ObjectMapper();
//        map = mapper.readValue(result, map.getClass());
//
//        //获取 code
//        Integer code = (Integer) map.get("code");
//
//        //获取 msg
//        String msg = (String) map.get("msg");
//        return msg;
//    }
//
//    private static Integer getId(String result) throws JsonProcessingException {
//        Integer newId = null;
//        Map<String, Object> map = new HashMap<>(16);
//        ObjectMapper mapper = new ObjectMapper();
//        map = mapper.readValue(result, map.getClass());
//
//        //获取 data
//        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");
//
//        if (resultMap != null) {
//            //获取 data
//            List<Map<String, Object>> records = (ArrayList) resultMap.get("records");
//            System.out.println(records);
//
//            if (records != null && records.size() == 1) {
//                Map<String, Object> recordMap = records.get(0);
//                newId = (Integer) recordMap.get("id");
//                System.out.println(newId);
//            } else {
//                System.out.println("ERROR to get Id, list is bigger than 1.");
//            }
//        }
//
//        return newId;
//    }

    /**
     * @throws Exception
     */
    @Test
    public void testAdd_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/add";

        DateFormat dateFormat = new SimpleDateFormat("_MM-dd_HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", "水库");
        bodyMap.put("label", "SHUI_KU");
        bodyMap.put("code", "01");
        bodyMap.put("remark", "测试类型描述" + dateString);

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
//        System.out.println(result);//运行方法，这里输出：
//        printResult(result);
        String msg = getMessage(result);
        System.out.println(msg);
        Assert.assertEquals("类型编码只能输入数字和大写字母，且不可以为00。", msg);
    }

    @Test
    public void testAdd_02() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/add";

        DateFormat dateFormat = new SimpleDateFormat("_MM-dd_HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("name", "测试" + dateString);
        bodyMap.put("label", "LB" + dateString);
        bodyMap.put("code", dateString);
        bodyMap.put("remark", "测试类型描述" + dateString);

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

        // 查
        uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/list";
        Map<String, Object> queryBodyMap = new HashMap<String, Object>();
        queryBodyMap.put("code", dateString);
        queryBodyMap.put("page", 1);
        queryBodyMap.put("rows", 20);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("code", dateString);
        queryBodyMap.put("query", queryMap);

        body = JSONObject.toJSONString(queryBodyMap);
        strEntity = new HttpEntity<String>(body, headers);
        result = restTemplate.postForObject(uri, strEntity, String.class);
        System.out.println(result);//运行方法，这里输出：
        printResult(result);
    }

    @Test
    public void testAdd_03() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/add";

        DateFormat dateFormat = new SimpleDateFormat("_MM-dd_HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);

        String label = "LBLBLBLsLBABLBLTLBa_";
        System.out.println(label.length());

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("name", "测试" + dateString);
        bodyMap.put("label", label);
        bodyMap.put("code", "A0"); // A4
        bodyMap.put("remark", "测试类型描述" + dateString);

        String body = JSONObject.toJSONString(bodyMap);

        String result = postForObject(restTemplate, uri, body);
        System.out.println(result);//运行方法，这里输出：
        printResult(result);
    }

    @Test
    public void testCRUD_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/add";

        String code = "CC";
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", code);
        bodyMap.put("label", code);
        bodyMap.put("code", code);
        bodyMap.put("remark", "测试类型描述" + code);

        String body = JSONObject.toJSONString(bodyMap);
        System.out.println("body: " + body);
        // 增
        String result = postForObject(restTemplate, uri, body);
        printResult(result);

        // 查
        uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/list";
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
            uri = URI_PREFIX + BS_PORT + "/api/standardized/object/object-type-meta/delete";
            Map<String, Object> deleteBodyMap = new HashMap<>();
            deleteBodyMap.put("id", id);
            body = JSONObject.toJSONString(deleteBodyMap);
            result = postForObject(restTemplate, uri, body);
            System.out.println(result);//运行方法，这里输出：
            printResult(result);
            System.out.println("删除成功：" + id);
        }
    }

}
