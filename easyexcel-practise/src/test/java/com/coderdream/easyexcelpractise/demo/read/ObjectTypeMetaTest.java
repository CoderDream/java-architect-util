package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.fastjson.JSONObject;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.client.ObjectTypeMetaServiceClient;
import com.keepsoft.microservice.dto.AttrMetaReqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 读的常见写法
 *
 * @author Jiaju Zhuang
 */
//@Ignore
//@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ObjectTypeMetaTest {

    @Autowired
    private ObjectTypeMetaServiceClient objectTypeServiceClient;

    @Test
    public void testCRUD_01() throws Exception {
        String result = "";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> bodyMap = new LinkedHashMap<String, Object>();
        String attrFullCode = "010000B010500";
        String objectFullCode = "0100020000000";
        bodyMap.put("attrFullCode", attrFullCode);
        bodyMap.put("objectFullCode", objectFullCode);
        String body = JSONObject.toJSONString(bodyMap);
        System.out.println(body);
        AttrMetaReqDto attrMetaReqDto = new AttrMetaReqDto();
        Result<String> result1 =  objectTypeServiceClient.genCode(attrMetaReqDto);
        System.out.println(result1);
//        Result<String> genCode(@RequestBody AttrMetaReqDto attrMetaReqDto)

//        result = postForObject(restTemplate, URI + "genCode", body);
//        String code = getCode(result);
//        System.out.println(code);
//
//        String attrItemName = "属性条目" + code;
//        String attrItemLabel = "TEST_ITEMA";
//        String attrItemCode = code;
//        String attrItemFullCode = "010002B00" + code + "00";
//        String dataType = "string";
//        String remark = "属性条目 " + code + "描述";
//        String hashRateLevel = "collection";
//        String dataSource = "model";
//
//        bodyMap.put("attrItemName", attrItemName);
//        bodyMap.put("attrItemLabel", attrItemLabel);
//        bodyMap.put("attrItemCode", attrItemCode);
//        bodyMap.put("attrItemFullCode", attrItemFullCode);
//        bodyMap.put("dataType", dataType);
//        bodyMap.put("objectFullCode", objectFullCode);
//        bodyMap.put("attrFullCode", attrFullCode);
//        bodyMap.put("remark", "测试属性类型描述" + remark);
//        bodyMap.put("hashRateLevel", hashRateLevel);
//        bodyMap.put("dataSource", dataSource);
//
//        body = JSONObject.toJSONString(bodyMap);
//        System.out.println("body: " + body);
//        // 增
//        result = postForObject(restTemplate, URI + "add", body);
//        printResult(result);
//
//        // 查
//        Map<String, Object> queryBodyMap = new LinkedHashMap<>();
//        queryBodyMap.put("current", 1);
//        queryBodyMap.put("size", 20);
//        queryBodyMap.put("attrItemFullCode", attrItemFullCode);
//
//        body = JSONObject.toJSONString(queryBodyMap);
//        result = postForObject(restTemplate, URI + "list", body);
//        System.out.println(result);
//
//        Integer id = getId(result);
//        if (id != null && id !=0) {
//            // 改
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

}
