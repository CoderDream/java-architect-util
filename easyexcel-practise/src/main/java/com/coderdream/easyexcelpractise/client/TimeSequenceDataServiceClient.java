package com.coderdream.easyexcelpractise.client;


import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.entity.TimeSequenceDataEntity;
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
@FeignClient(name = "api/object/data/fdll", path = "api/object/data/fdll", url = "http://127.0.0.1:9099")
public interface TimeSequenceDataServiceClient {

    @ApiOperation(value = "时间序列数据", notes = "时间序列数据")
    @PostMapping("/insertBatch")
    Result<Integer> insertBatch(@RequestBody List<TimeSequenceDataEntity> entities);

}
