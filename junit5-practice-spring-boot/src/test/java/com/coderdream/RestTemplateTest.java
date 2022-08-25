package com.coderdream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestTemplateTest<ResultVo> {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void shouldAnswerWithTrue() throws Exception {
        //assertTrue( true );
        try {
            String url = "http://localhhost:9001/test/demo1";
            ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(url, null, Object.class);
            if( 200 == objectResponseEntity.getStatusCodeValue()){
               // return ResultVo.success();
            }
            //return ResultVo.error();
        } catch (Exception e) {
            throw new Exception("调用接口失败," + e.getMessage());
        }
    }


    @Test
    public void test1() throws Exception {

        final String uri = "http://172.16.104.61:30572/api/data/sys/captchaImage";


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }



    @Test
    public void test2() throws Exception {
//        List<ProductTypeVO> voList = Lists.newArrayList();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        headers.add("key1","value1");
//        headers.add("key2","value2");
//
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//        ProductTypeVO[] responseEntity = restTemplate.postForObject(
//                url,
//                entity,
//                ProductTypeVO[].class);
//
//        if (null != responseEntity) {
//            voList = Arrays.asList(responseEntity);
//        }

        final String uri = "http://172.16.104.61:30572/api/data/sys/captchaImage";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("key1","value1");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }



}
