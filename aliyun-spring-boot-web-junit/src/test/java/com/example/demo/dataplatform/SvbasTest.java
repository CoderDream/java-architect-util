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
@SpringBootTest(classes = {SvbasTest.class}) //测试的class
@ContextConfiguration(classes = SvbasTest.class)
public class SvbasTest extends DataBaseTest {

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        URI = URI_PREFIX + BUS_PORT + "/api/data/bus/svbas/";
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
        body = "{\"id\":385,\"code\":\"jcsj/zdjbxx/ll2\",\"stId\":44,\"gpId\":-1,\"name\":\"流量\",\"type\":1,\"tabeName\":null,\"status\":1,\"description\":\"\",\"localFlag\":null,\"svFlag\":0,\"createBy\":null,\"createTime\":\"2022-11-24 19:17:26\",\"updateBy\":null,\"updateTime\":null,\"delFlag\":1,\"dbName\":\"aj_bus_prod\",\"localType\":3,\"cron\":null,\"relForm\":null,\"fdForm\":null,\"cfgForm\":null,\"qyForm\":null,\"labForm\":null,\"paramForm\":null,\"selFag\":0,\"pstName\":\"\",\"searchDate\":null,\"searchStaCode\":null,\"infeName\":\"流量\",\"callName\":\"流量\",\"reqMode\":\"post\",\"reqType\":\"application/json\",\"rtType\":\"application/json\",\"serviceName\":\"站点基本信息\",\"serviceId\":4,\"subId\":385,\"pmForms\":[{\"id\":91,\"name\":\"mp_cd\",\"headerFlag\":0,\"description\":null,\"dfVal\":\"\",\"reqdFlg\":0,\"pmType\":5,\"pmTypeFlag\":false,\"compTypeFlag\":false,\"compType\":\"=\",\"descriptionFlag\":false}],\"rtForms\":[{\"id\":3446,\"name\":\"mp_cd\",\"description\":null,\"rtType\":8,\"rtTypeFlag\":false},{\"id\":7445,\"name\":\"tm\",\"description\":null,\"rtType\":8,\"rtTypeFlag\":false}],\"userId\":8034}";
        result = postForObject(restTemplate, URI + "save", body);
        System.out.println(result);
    }

}
