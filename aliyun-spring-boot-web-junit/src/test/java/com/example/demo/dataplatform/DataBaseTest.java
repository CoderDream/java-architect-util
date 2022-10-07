package com.example.demo.dataplatform;

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
@SpringBootTest(classes = {DataBaseTest.class}) //测试的class
@ContextConfiguration(classes = DataBaseTest.class)
public class DataBaseTest {
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
     * URI
     */
    public String URI;

    /**
     * token
     */
    public String token;

    /**
     * secret
     */
    public String secret;

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
            case "local_home":
                URI_PREFIX = "http://192.168.3.85:";
                ADMIN_PORT = "9085";
                BUS_PORT = "9086";
                DS_PORT = "7080";
                BS_PORT = "9085";
                break;
            case "home_123":
                URI_PREFIX = "http://192.168.3.123:";
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

        final String uri = URI_PREFIX + ADMIN_PORT + "/api/data/sys/login";

        RestTemplate restTemplate = new RestTemplate();
        // {"username":"xulin12345","password":"7191fb545e1f2de334fe8d38d5af905e","code":"0","uuid":"9dfb4a766f26425aa94118c92820ee0d"}
//        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"password\":\"7191fb545e1f2de334fe8d38d5af905e\",\"username\":\"xulin12345\",\"code\":\"4\"}";//实例请求参数
        String user = "{\"username\":\"admin\",\"password\":\"21232f297a57a5a743894a0e4a801fc3\",\"code\":\"0\",\"uuid\":\"b17bb5fb38494928a63c4f4e62829064\"}";//实例请求参数
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
        // System.out.println(result);//运行方法，这里输出：

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

        //获取 captchaOnOff
        //  List roles = (List) dataMap.get("roles");
        // System.out.println(roles.get(0));

        //获取 secret
        String tempSecret = (String) dataMap.get("secret");
        // System.out.println(tempSecret);
        secret = tempSecret;
        //获取 token
        String tempToken = (String) dataMap.get("token");
        // System.out.println(tempToken);
        token = tempToken;
    }

    @Test
    @Ignore
    public void testGetCaptchaImage() throws Exception {
//        final String uri = URI_PREFIX + "30572/api/data/sys/captchaImage";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//
//        System.out.println(result);
//
//        Map<String, Object> map = new LinkedHashMap<>(16);
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
    @Ignore
    public void testLogin() throws Exception {
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
        System.out.println(uri);
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
    public void testFindData() throws Exception {
        System.out.println("testFindData");
        // final String uri = URI_PREFIX + "9519/api/data/ds/app/findData?userId=admin";
        final String uri = URI_PREFIX + "9519/api/data/ds/app/findData?userId=xulin";

        ResponseEntity<String> resultMap = process(uri);


        //     System.out.println(resultMap);
        String result = resultMap.getBody();

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
        List<Map<String, Object>> mapList = (ArrayList) map.get("data");
        System.out.println(mapList);
        for (Map<String, Object> tempMap : mapList) {
            System.out.println(tempMap);
            String appName = (String) tempMap.get("appName");
            System.out.println(appName);

            Integer appId = (Integer) tempMap.get("appId");
            System.out.println(appId);

            String status = (String) tempMap.get("status");
            System.out.println(status);
        }
    }

    private ResponseEntity<String> process(String uri) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("secret", secret);
        headers.add("token", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return resultMap;
    }

    @Test
    public void testFindParam() throws Exception {
        System.out.println("testFindParam");
        // final String uri = URI_PREFIX + "9519/api/data/ds/app/findData?userId=admin";
        final String uri = URI_PREFIX + "9086/api/data/bus/svbas/findParam?subId=366";

        ResponseEntity<String> resultMap = process(uri);


        //     System.out.println(resultMap);
        String result = resultMap.getBody();

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
        List<Map<String, Object>> mapList = (ArrayList) map.get("data");
        System.out.println(mapList);
        for (Map<String, Object> tempMap : mapList) {
            System.out.println(tempMap);
            String appName = (String) tempMap.get("appName");
            System.out.println(appName);

            Integer appId = (Integer) tempMap.get("appId");
            System.out.println(appId);

            String status = (String) tempMap.get("status");
            System.out.println(status);
        }
    }

    @Test
    public void testHandleUpdate() throws Exception {
        final String uri = URI_PREFIX + DS_PORT + "/dataCollection/handle/update";
        RestTemplate restTemplate = new RestTemplate();

        String user = "{\"id\":\"3333333333\",\"name\":\"测试\",\"target\":\"g_team_copy1\",\"catalogue\":\"e96e16982e0541079d06a7618af76ef1\",\"rule\":\"f501172dedd34a4abe610dde0553cc5b\",\"quartzDescription\":\"无\",\"cycle\":\"每年\",\"quartzCron\":\"0 32 17 * * ?\",\"addTime\":\"2022-08-24 19:20:17\",\"addUser\":null,\"editTime\":\"2022-08-24 18:02:22\",\"editUser\":null,\"flag\":\"\",\"ruleNum\":0,\"company\":\"我\",\"connect\":\"xsws_test_120mysql\",\"connectId\":\"22\"}";//实例请求参数
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

        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 code
        String code = (String) map.get("code");
        System.out.println(code);

        //获取 msg
        String msg = (String) map.get("message");
        System.out.println(msg);
    }

    @Test
    public void testGetTbFieldInfo() throws Exception {
        System.out.println("testGetTbFieldInfo");
        //final String uri = URI_PREFIX + "9086/api/data/bus/attdtdb/getTbFieldInfo?id=7&tbName=asset_catalog";
        final String uri = URI_PREFIX + BUS_PORT + "/api/data/bus/attdtdb/getTbFieldInfo?id=7&tbName=asset_catalog";

        ResponseEntity<String> resultMap = process(uri);

        System.out.println(resultMap);
        String result = resultMap.getBody();

        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 code
        Integer code = (Integer) map.get("code");
        System.out.println(code);

        //获取 msg
        String msg = (String) map.get("msg");
//        System.out.println(msg);

        // TODO
        //获取 data
        List<Map<String, Object>> mapList = (ArrayList) map.get("data");
//        System.out.println(mapList);
        for (Map<String, Object> tempMap : mapList) {
//            System.out.println(tempMap);
            String columnComment = (String) tempMap.get("columnComment");
            System.out.println("columnComment: \t" + columnComment);

            Integer columnLen = (Integer) tempMap.get("columnLen");
            System.out.println("columnLen: \t" + columnLen);

            String columnName = (String) tempMap.get("columnName");
            System.out.println("columnName: \t" + columnName);

            String dataType = (String) tempMap.get("dataType");
            System.out.println("dataType: \t" + dataType);
        }
    }

    @Test
    public void testGetData() throws Exception {
        final String uri = URI_PREFIX + BUS_PORT + "/api/data/bus/data/getData";
        RestTemplate restTemplate = new RestTemplate();
        //405 394
        String user = "{\"dataId\":405,\"type\":4,\"current\":1,\"size\":100}";//实例请求参数
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

        Map<String, Object> map = new LinkedHashMap<>(16);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, map.getClass());

        //获取 code
        Integer code = (Integer) map.get("code");
        System.out.println(code);

        //获取 msg
        String msg = (String) map.get("message");
        System.out.println(msg);

        //获取 data
        //获取 data
        Map<String, Object> dataMap = (LinkedHashMap) map.get("data");

        List<Map<String, Object>> mapList = (ArrayList) dataMap.get("data");
      //  System.out.println(mapList);
        for (Map<String, Object> tempMap : mapList) {
            System.out.println(tempMap);
//            String columnComment = (String) tempMap.get("columnComment");
//            System.out.println("columnComment: \t" + columnComment);
//
//            Integer columnLen = (Integer) tempMap.get("columnLen");
//            System.out.println("columnLen: \t" + columnLen);
//
//            String columnName = (String) tempMap.get("columnName");
//            System.out.println("columnName: \t" + columnName);
//
//            String dataType = (String) tempMap.get("dataType");
//            System.out.println("dataType: \t" + dataType);
        }
    }


    @Test
    public void testFindDataSIT() throws Exception {
        final String uri = "http://172.16.104.131:30965/api/data/ds/app/findData?userId=admin";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("secret", "c4ca4238a0b923820dcc509a6f75849b");
        headers.add("token", "0f147fc700124a4e997bf94fa1fca79c");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);


        //     System.out.println(resultMap);
        String result = resultMap.getBody();

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
        List<Map<String, Object>> mapList = (ArrayList) map.get("data");
        System.out.println(mapList);
        for (Map<String, Object> tempMap : mapList) {
            System.out.println(tempMap);
            String appName = (String) tempMap.get("appName");
            System.out.println(appName);

            Integer appId = (Integer) tempMap.get("appId");
            System.out.println(appId);

            String status = (String) tempMap.get("status");
            System.out.println(status);
        }
//
//        //获取 captchaOnOff
//        List roles = (List) dataMap.get("roles");
//        System.out.println(roles.get(0));
//
//        //获取 secret
//        String secret = (String) dataMap.get("secret");
//        System.out.println(secret);
//
//        //获取 token
//        String token = (String) dataMap.get("token");
//        System.out.println(token);
    }

    public String postForObject(RestTemplate restTemplate, String uri, String body) {
        HttpHeaders headers = new HttpHeaders();//创建一个头部对象
        //设置contentType 防止中文乱码
        headers.setContentType(MediaType.valueOf("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); // TODO
        headers.add("secret", secret);
        headers.add("token", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        //设置我们的请求信息，第一个参数为请求Body,第二个参数为请求头信息
        //完整的方法签名为：HttpEntity<String>(String body, MultiValueMap<String, String> headers)
        HttpEntity<String> strEntity = new HttpEntity<>(body, headers);
        //使用post方法提交请求，第一参数为url,第二个参数为我们的请求信息,第三个参数为我们的相应放回数据类型，与String result对厅
        //完整的方法签名为：postForObject(String url, Object request, Class<String> responseType, Object... uriVariables) ，最后的uriVariables用来拓展我们的请求参数内容。
        return restTemplate.postForObject(uri, strEntity, String.class);
    }

    public String getForObject(RestTemplate restTemplate, String uri, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("secret", secret);
        headers.add("token", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return resultMap.getBody();
    }

    @Test
    // @Ignore
    public void testGetSecretAndToken_01() throws Exception {
        System.out.println("login_tokens:" + secret + ":" + token);
    }

}
