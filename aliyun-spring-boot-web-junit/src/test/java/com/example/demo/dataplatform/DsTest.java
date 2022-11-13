package com.example.demo.dataplatform;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")  //你的配置文件
@SpringBootTest(classes = {DsTest.class}) //测试的class
@ContextConfiguration(classes = DsTest.class)
public class DsTest extends DataBaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + DS_PORT + "/dataCollection/dicategory/";
    }

    //
    // 0a63c9e7711928fbf7ece4c6eba7514a

    @Test
    // @Ignore
    public void testSave_01() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;

        System.out.println("login_tokens:" + secret + ":" + token);
        // 保存
//        Map<String, Object> saveBodyMap = new LinkedHashMap<>();
//        body = "{\"name\":\"ANJ1\",\"description\":\"aj-bus\",\"drive\":\"com.mysql.jdbc.Driver\",\"url\":\"jdbc:mysql://172.16.107.164:3309/aj-bus?useUnicode=true&characterEncoding=utf8\",\"username\":\"aj-bus\",\"password\":\"aj-bus@123456\",\"mainUser\":\"aj-bus\",\"department\":\"aj-bus\",\"id\":\"\",\"dbType\":\"MySQL\",\"dir\":\"45\",\"maxActive\":\"20\",\"waitTime\":\"10000\",\"logLevel\":\"ERROR\",\"ext\":\"\",\"gpId\":\"\"}"; //JSONObject.toJSONString(saveBodyMap);
//        result = postForObject(restTemplate, URI + "save", body);
//        System.out.println(result);


        //     System.out.println(resultMap);
//        String result = resultMap.getBody();

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
//        List<Map<String, Object>> mapList = (ArrayList) map.get("data");
//        System.out.println(mapList);
//        for (Map<String, Object> tempMap : mapList) {
//            System.out.println(tempMap);
//            String appName = (String) tempMap.get("appName");
//            System.out.println(appName);
//
//            Integer appId = (Integer) tempMap.get("appId");
//            System.out.println(appId);
//
//            String status = (String) tempMap.get("status");
//            System.out.println(status);
//        }

        //final String uri = "http://172.16.104.61:30572/api/data/sys/loginWithUsername";
//        final String uri = "http://192.168.18.75:9085/api/data/sys/loginWithUsername";
//        RestTemplate restTemplate = new RestTemplate();
//
//        String user = "{\"uuid\":\"5ff4a5eb88314323aaedc37978239c38\",\"username\":\"xulin1234\",\"code\":\"4\"}";//实例请求参数
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
//        System.out.println(result);//运行方法，这里输出：
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
//        List roles = (List) dataMap.get("roles");
//        if(roles != null && roles.size() > 0) {
//            System.out.println(roles.get(0));
//        }
//
//        //获取 secret
//        String secret = (String) dataMap.get("secret");
//        System.out.println(secret);
//
//        //获取 token
//        String token = (String) dataMap.get("token");
//        System.out.println(token);
    }

    @Test
    // @Ignore
    public void testSave() {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;

        System.out.println("login_tokens:" + secret + ":" + token);
        // 保存
        Map<String, Object> saveBodyMap = new LinkedHashMap<>();
        body = "{\"name\":\"ANJ41\",\"description\":\"aj-bus\",\"drive\":\"com.mysql.jdbc.Driver\",\"url\":\"jdbc:mysql://172.16.107.164:3309/aj-bus?useUnicode=true&characterEncoding=utf8\",\"username\":\"aj-bus\",\"password\":\"aj-bus@123456\",\"mainUser\":\"aj-bus\",\"department\":\"aj-bus\",\"id\":\"\",\"dbType\":\"MySQL\",\"dir\":\"45\",\"maxActive\":\"20\",\"waitTime\":\"10000\",\"logLevel\":\"ERROR\",\"ext\":\"\",\"gpId\":\"\"}"; //JSONObject.toJSONString(saveBodyMap);
        result = postForObject(restTemplate, URI + "save", body);
        System.out.println(result);
    }

    /**
     * <pre>
     *     {
     * 	"name": "ANJ4",
     * 	"description": "aj-bus1",
     * 	"drive": "com.mysql.jdbc.Driver",
     * 	"url": "jdbc:mysql://172.16.107.164:3309/aj-bus?useUnicode=true&characterEncoding=utf8",
     * 	"username": "aj-bus",
     * 	"password": "aj-bus@123456",
     * 	"mainUser": "aj-bus",
     * 	"department": "aj-bus",
     * 	"high": true,
     * 	"id": 168,
     * 	"gpId": "",
     * 	"dir": "45",
     * 	"maxActive": 20,
     * 	"waitTime": 10000,
     * 	"logLevel": "ERROR",
     * 	"ext": "",
     * 	"createTime": "2022-09-27 16:15:54",
     * 	"createBy": "xulin12345",
     * 	"updateTime": null,
     * 	"updateBy": null,
     * 	"delFlag": 1,
     * 	"dbType": "MySQL",
     * 	"clickType": "edit"
     * }
     *
     * </pre>
     */
    @Test
    // @Ignore
    public void testUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;

        System.out.println("login_tokens:" + secret + ":" + token);
        // 保存
        Map<String, Object> saveBodyMap = new LinkedHashMap<>();
        body = "{\"name\":\"ANJ4\",\"description\":\"aj-bus4\",\"drive\":\"com.mysql.jdbc.Driver\",\"url\":\"jdbc:mysql://172.16.107.164:3309/aj-bus?useUnicode=true&characterEncoding=utf8\",\"username\":\"aj-bus\",\"password\":\"aj-bus@123456\",\"mainUser\":\"aj-bus\",\"department\":\"aj-bus\",\"high\":true,\"id\":168,\"gpId\":\"\",\"dir\":\"45\",\"maxActive\":20,\"waitTime\":10000,\"logLevel\":\"ERROR\",\"ext\":\"\",\"createTime\":\"2022-09-27 16:15:54\",\"createBy\":\"xulin12345\",\"updateTime\":null,\"updateBy\":null,\"delFlag\":1,\"dbType\":\"MySQL\",\"clickType\":\"edit\"}"; //JSONObject.toJSONString(saveBodyMap);
        result = postForObject(restTemplate, URI + "update", body);
        System.out.println(result);
    }

    @Test
    // @Ignore
    public void testList_01() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String result = "";
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String body;
        ResponseEntity<String> resultMap;
        System.out.println("login_tokens:" + secret + ":" + token);
        // http://172.16.104.61:31582/api/data/bus/attdtdb/list?size=20&current=2&name=&gpId=
        URI = URI + "list?size=100&current=1";
        // 保存
        Map<String, Object> saveBodyMap = new LinkedHashMap<>();
        body = "{\"name\":\"ANJ4\",\"description\":\"aj-bus\",\"drive\":\"com.mysql.jdbc.Driver\",\"url\":\"jdbc:mysql://172.16.107.164:3309/aj-bus?useUnicode=true&characterEncoding=utf8\",\"username\":\"aj-bus\",\"password\":\"aj-bus@123456\",\"mainUser\":\"aj-bus\",\"department\":\"aj-bus\",\"id\":\"\",\"dbType\":\"MySQL\",\"dir\":\"45\",\"maxActive\":\"20\",\"waitTime\":\"10000\",\"logLevel\":\"ERROR\",\"ext\":\"\",\"gpId\":\"\"}"; //JSONObject.toJSONString(saveBodyMap);
        System.out.println(URI);
        result = getForObject(restTemplate, URI, body);

        System.out.println(result);
    }


}
