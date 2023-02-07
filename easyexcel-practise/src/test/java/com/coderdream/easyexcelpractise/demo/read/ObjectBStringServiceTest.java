package com.coderdream.easyexcelpractise.demo.read;

import com.alibaba.fastjson.JSONObject;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.client.*;
import com.coderdream.easyexcelpractise.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
public class ObjectBStringServiceTest {

    @Autowired
    private ObjectBStringServiceClient objectBStringServiceClient;

    @Test
    public void cellDataRead_01() {

        ObjectAttrReqDto objectAttrReqDto = new ObjectAttrReqDto();
        objectAttrReqDto.setObjectFullCode("14 0000 0 00 00 00");

        Result<List<ItemInfoRespDto>> result = objectBStringServiceClient.list(objectAttrReqDto);
        System.out.println(result.getResult());
        if (result != null && result.getResult() != null && !CollectionUtils.isEmpty(result.getResult())) {
            List<ItemInfoRespDto> list = result.getResult();
            System.out.println(JSONObject.toJSONString(list));
        }
    }
}