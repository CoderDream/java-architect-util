package com.example.demo.codesystem;

import com.example.demo.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class BaseTest {
    @Value("${env}")
    public String env;

    public String URI_PREFIX;

    /**
     * admin Service
     */
    public String ADMIN_PORT;

    public String BUS_PORT;

    /**
     * Data Service Port
     */
    public String DS_PORT;

    /**
     * Business Service Port
     */
    public String BS_PORT;


    /**
     * Object Data Service Port
     */
    public String OBJECT_DATA_PORT;

    /**
     * URI
     */
    public String URI;


    public void init() throws JsonProcessingException {
        switch (env) {
            case "local":
                URI_PREFIX = "http://192.168.18.75:";
                ADMIN_PORT = "9085";
                BUS_PORT = "9086";
                DS_PORT = "7080";
                BS_PORT = "9085";
                OBJECT_DATA_PORT = "9099";
                break;
            case "local_home":
                URI_PREFIX = "http://192.168.3.85:";
                ADMIN_PORT = "9085";
                BUS_PORT = "9086";
                DS_PORT = "7080";
                BS_PORT = "9085";
                OBJECT_DATA_PORT = "9099";
                break;
            case "sit":
                URI_PREFIX = "http://172.16.104.61:";
                ADMIN_PORT = "30572";
                BUS_PORT = "30965";
                DS_PORT = "7080";
                OBJECT_DATA_PORT = "9099";
                break;
            case "so-sit":
                URI_PREFIX = "http://172.16.104.131:";
                ADMIN_PORT = "30572";
                BUS_PORT = "32355";
                DS_PORT = "7080";
                BS_PORT = "32355";
                OBJECT_DATA_PORT = "30417"; // http://172.16.104.131:30417/
                break;
            case "nas":
                URI_PREFIX = "http://27.19.125.63:";
                ADMIN_PORT = "30572";
                BUS_PORT = "30965";
                DS_PORT = "7080";
                OBJECT_DATA_PORT = "9099";
                break;
        }
    }


    public static void printResult(String result) throws JsonProcessingException {
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
        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");
        System.out.println(resultMap);

        if (resultMap != null) {
            //获取 data
            List<Map<String, Object>> records = (ArrayList) resultMap.get("records");
            System.out.println(records);

            for (Map<String, Object> recordMap : records) {
                System.out.println((Integer) recordMap.get("id"));
            }
        }
    }

    public static String getMessage(String result) throws JsonProcessingException {
        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());


        String json = "{ \"name\" : \"bob\" }";
        ObjectMapper mapper2 = new ObjectMapper();
        User user = mapper2.readValue(json, User.class);
        //获取 msg
        return (String) map.get("msg");
    }

    public static Integer getId(String result) throws JsonProcessingException {
        Integer newId = null;
        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 data
        Map<String, Object> resultMap = (LinkedHashMap) map.get("result");

        if (resultMap != null) {
            //获取 data
            List<Map<String, Object>> records = (ArrayList) resultMap.get("records");
            System.out.println(records);

            if (records != null && records.size() == 1) {
                Map<String, Object> recordMap = records.get(0);
                newId = (Integer) recordMap.get("id");
                System.out.println(newId);
            } else {
                System.out.println("ERROR to get Id, list is bigger than 1.");
            }
        }

        return newId;
    }

    public static String getCode(String result)  {
        Integer newId = null;
        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(result, map.getClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //获取 msg
        return (String) map.get("msg");
    }


    public static String postForObject(RestTemplate restTemplate, String uri, String body) {
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        return restTemplate.postForObject(uri, strEntity, String.class);
    }

}
