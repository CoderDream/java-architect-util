package com.coderdream.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.coderdream.passbook.constant.Constants;
import com.coderdream.passbook.constant.ErrorCode;
import com.coderdream.passbook.dao.MerchantsDao;
import com.coderdream.passbook.entity.Merchants;
import com.coderdream.passbook.service.IMerchantsService;
import com.coderdream.passbook.vo.CreateMerchantsRequest;
import com.coderdream.passbook.vo.CreateMerchantsResponse;
import com.coderdream.passbook.vo.PassTemplate;
import com.coderdream.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <h1>商户服务接口实现</h1>
 * Created by CoderDream.
 */
@Slf4j
@Service
public class MerchantsServiceImpl implements IMerchantsService {

    /**
     * Merchants 数据库接口
     */
    private final MerchantsDao merchantsDao;

    /**
     * kafka 客户端
     */
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantsServiceImpl(MerchantsDao merchantsDao,
                                KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {

        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            Merchants merchants = merchantsDao.save(request.toMerchants());
            merchantsResponse.setId(merchants.getId());
        }

        response.setData(merchantsResponse);

        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {

        Response response = new Response();

        Optional<Merchants> optionalMerchants = merchantsDao.findById(id);
        if (null == optionalMerchants) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        Merchants merchants = optionalMerchants.get();
        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplates: {}", passTemplate);
        }

        return response;
    }
}
