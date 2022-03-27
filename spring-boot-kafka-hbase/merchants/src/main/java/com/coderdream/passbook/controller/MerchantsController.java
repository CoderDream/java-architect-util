package com.coderdream.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.coderdream.passbook.service.IMerchantsService;
import com.coderdream.passbook.vo.CreateMerchantsRequest;
import com.coderdream.passbook.vo.PassTemplate;
import com.coderdream.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>商户服务 Controller</h1>
 * Created by CoderDream.
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    /** 商户服务接口 */
    private final IMerchantsService merchantsService;

    @Autowired
    public MerchantsController(IMerchantsService merchantsService) {
        this.merchantsService = merchantsService;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {

        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantsService.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id) {

        log.info("BuildMerchantsInfo: {}", id);
        return merchantsService.buildMerchantsInfoById(id);
    }

    /**
     * DropPassTemplates: {"background":1,"desc":"详情: 慕课 second",
     * "end":1528202373202,"hasToken":false,"id":9,"limit":1000,
     * "start":1527338373202,"summary":"简介: 慕课","title":"title: 慕课"}
     * */
    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {

        log.info("DropPassTemplate: {}", passTemplate);
        return merchantsService.dropPassTemplate(passTemplate);
    }
}
