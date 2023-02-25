package com.coderdream.easyexcelpractise.client;


import com.coderdream.easyexcelpractise.Result;
import com.keepsoft.microservice.dto.ItemInfoRespDto;
import com.keepsoft.microservice.dto.ObjectAttrReqDto;
import com.coderdream.easyexcelpractise.entity.ObjectBStringEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
 * url: url一般用于调试，可以手动指定@FeignClient调用的地址
 * path: 定义当前FeignClient的统一前缀 http://172.16.104.131:30417/
 */
//@FeignClient(name = "keepsoft-object-data", path = "api/object/data/object/attr", url = "http://172.16.104.131:30417")
@FeignClient(name = "keepsoft-object-data-object-b-string", path = "api/object/data/objectBString", url = "http://127.0.0.1:9099")
public interface ObjectBStringServiceClient {

    /**
     * 调用接口返回数据
     *
     * @return
     */
    @PostMapping("/list")
    Result<List<ItemInfoRespDto>> list(@RequestBody ObjectAttrReqDto objectAttrReqDto);


    @ApiOperation(value = "插入短文本对象属性条目数据", notes = "插入短文本对象属性条目数据")
    @PostMapping("/insertBatch")
    Result<Integer> insertBatch(@RequestBody List<ObjectBStringEntity> objectBStringEntities);

}
