package com.coderdream.passbook.service;

import com.alibaba.fastjson.JSON;
import com.coderdream.passbook.vo.CreateMerchantsRequest;
import com.coderdream.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <h1>商户服务测试类</h1>
 * Created by CoderDream.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest {

    @Autowired
    private IMerchantsService merchantsService;

    /**
     * {"data":{"id":7},"errorCode":0,"errorMsg":""}
     * {"data":{"id":8},"errorCode":0,"errorMsg":""}
     * {"data":{"id":-1},"errorCode":1,"errorMsg":"商户名称重复"}
     * */
    @Test
//    @Transactional
    public void testCreateMerchantService() {

        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("慕课2");
        request.setLogoUrl("www.imooc.com2");
        request.setBusinessLicenseUrl("www.imooc.com2");
        request.setPhone("12345678902");
        request.setAddress("北京市2");

        System.out.println(JSON.toJSONString(merchantsService.createMerchants(request)));
    }

    /**
     * {
     * 	"data": {
     * 		"address": "北京市",
     * 		"businessLicenseUrl": "www.imooc.com",
     * 		"id": 1,
     * 		"isAudit": false,
     * 		"logoUrl": "www.imooc.com",
     * 		"name": "慕课",
     * 		"phone": "1234567890"
     *        },
     * 	"errorCode": 0,
     * 	"errorMsg": ""
     * }
     *
     * */
    @Test
    public void testBuildMerchantsInfoById() {
        System.out.println(JSON.toJSONString(merchantsService.buildMerchantsInfoById(1)));
    }

    /**
     * DropPassTemplates: {"background":2,"desc":"详情: 慕课",
     * "end":1528202373202,"hasToken":false,"id":9,"limit":10000,
     * "start":1527338373202,"summary":"简介: 慕课","title":"title: 慕课"}
     * */
    @Test
    public void testDropPassTemplate() {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(1);
        passTemplate.setTitle("慕课-1");
        passTemplate.setSummary("简介: 慕课");
        passTemplate.setDesc("详情: 慕课");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(), -10));
        passTemplate.setEnd(DateUtils.addDays(new Date(), 10));

        System.out.println(JSON.toJSONString(
                merchantsService.dropPassTemplate(passTemplate)
        ));
    }
}
