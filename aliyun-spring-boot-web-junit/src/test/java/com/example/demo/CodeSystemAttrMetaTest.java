package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {CodeSystemAttrMetaTest.class}) //测试的class
@ContextConfiguration(classes = CodeSystemAttrMetaTest.class)
public class CodeSystemAttrMetaTest {


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

    @Before
    public void init() throws JsonProcessingException {
        switch (env) {
            case "local":
                URI_PREFIX = "http://192.168.18.75:";
                ADMIN_PORT = "9085";
                BUS_PORT = "9086";
                DS_PORT = "7080";
                break;
            case "sit":
                URI_PREFIX = "http://172.16.104.61:";
                ADMIN_PORT = "30572";
                BUS_PORT = "30965";
                DS_PORT = "7080";
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
//        final String uri = "http://192.168.18.75:9085/api/data/sys/login";

        final String uri = URI_PREFIX + "9085/api/standardized/object/attr-item-meta/list";
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
    public void testLogin03() throws Exception {
        final String uri = "http://172.16.104.61:30572/api/data/sys/login";
        RestTemplate restTemplate = new RestTemplate();

        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"password\":\"c0e0857806d4fb4769bd4d9f501dbe6d\",\"username\":\"xulin\",\"code\":\"4\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(user, headers);
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

        //获取 captchaOnOff
        List roles = (List) dataMap.get("roles");
        System.out.println(roles.get(0));

        //获取 secret
        String secret = (String) dataMap.get("secret");
        System.out.println(secret);

        //获取 token
        String token = (String) dataMap.get("token");
        System.out.println(token);
    }

    @Test
    public void testLogin02() throws Exception {
        final String uri = "http://192.168.18.75:9085/api/data/sys/login";
        RestTemplate restTemplate = new RestTemplate();

//        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"password\":\"c0e0857806d4fb4769bd4d9f501dbe6d\",\"username\":\"xulin\",\"code\":\"4\"}";//实例请求参数
        String user = "{\"username\":\"xulin12345\",\"password\":\"0a63c9e7711928fbf7ece4c6eba7514a\",\"code\":\"12\",\"uuid\":\"7f5191ad23b946eebd32271255fe9341\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(user, headers);
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

        //获取 captchaOnOff
        List roles = (List) dataMap.get("roles");
        System.out.println(roles.get(0));

        //获取 secret
        String secret = (String) dataMap.get("secret");
        System.out.println(secret);

        //获取 token
        String token = (String) dataMap.get("token");
        System.out.println(token);
    }

    @Test
   // @Ignore
    public void testLoginWithUsername() throws Exception {
        //final String uri = "http://172.16.104.61:30572/api/data/sys/loginWithUsername";
        final String uri = "http://192.168.18.75:9085/api/data/sys/loginWithUsername";
        RestTemplate restTemplate = new RestTemplate();

        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"username\":\"xulin12345\",\"code\":\"4\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(user, headers);
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

        //获取 captchaOnOff
        List roles = (List) dataMap.get("roles");
        if(roles != null && roles.size() > 0) {
            System.out.println(roles.get(0));
        }

        //获取 secret
        String secret = (String) dataMap.get("secret");
        System.out.println(secret);

        //获取 token
        String token = (String) dataMap.get("token");
        System.out.println(token);
    }

    @Test
    // @Ignore
    public void testLoginWithUsernameSIT() throws Exception {
        //final String uri = "http://172.16.104.61:30572/api/data/sys/loginWithUsername";
        final String uri = "http://192.168.18.75:9085/api/data/sys/loginWithUsername";
        RestTemplate restTemplate = new RestTemplate();

        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"username\":\"xulin1234\",\"code\":\"4\"}";//实例请求参数
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8.toString());
        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<String>(user, headers);
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

        //获取 captchaOnOff
        List roles = (List) dataMap.get("roles");
        if(roles != null && roles.size() > 0) {
            System.out.println(roles.get(0));
        }

        //获取 secret
        String secret = (String) dataMap.get("secret");
        System.out.println(secret);

        //获取 token
        String token = (String) dataMap.get("token");
        System.out.println(token);
    }

    //



}
