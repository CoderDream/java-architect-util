package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {RestTemplateSingleTest.class}) //测试的class
@ContextConfiguration(classes = RestTemplateSingleTest.class)
public class RestTemplateSingleTest {


    @Test
    @Ignore
    public void testGetCaptchaImage() throws Exception {
//        final String uri = URI_PREFIX + "30572/api/data/sys/captchaImage";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//
//        System.out.println(result);
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
//
//        //获取 captchaOnOff
//        Boolean captchaOnOff = (Boolean) dataMap.get("captchaOnOff");
//        System.out.println(captchaOnOff);
    }

    @Test
    public void testLogin01() throws Exception {
        final String uri = "http://192.168.18.75:9085/api/data/sys/login";
        RestTemplate restTemplate = new RestTemplate();

        String user = "{\"username\":\"xulin123\",\"password\":\"9f2c5ea989dfeef6476cbcb8c01dfa96\",\"code\":\"1\",\"uuid\":\"7671db3ecb4e461aa7abe5afbd64de0b\"}";//实例请求参数
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
