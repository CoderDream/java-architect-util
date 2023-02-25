package com.coderdream.easyexcelpractise.client;


import com.coderdream.easyexcelpractise.Result;
import com.keepsoft.microservice.dto.ObjectAttrReqDto;
import com.coderdream.easyexcelpractise.entity.ForecastDataEntity;
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
//@FeignClient(name = "keepsoft-object-data-forecast-data", path = "api/object/data/forecast/data", url = "http://172.16.104.131:30417")
@FeignClient(name = "keepsoft-object-data-forecast-data", path = "api/object/data/forecast/data", url = "http://127.0.0.1:9099")
public interface ForecastDataServiceClient {

    /**
     * 调用接口返回数据
     *
     * @return
     */
    @PostMapping("/list")
    Result list(@RequestBody List<ObjectAttrReqDto> objectAttrReqDtoList);

//    /**
//     * 获取对象地理信息列表
//     *
//     * @return 列表
//     */
//    @ApiOperation(value = "对象地理信息列表查询", notes = "对象地理信息列表查询")
//    @PostMapping("/list")
//    Result<List<ItemInfoRespDto>> list(@RequestBody ObjectAttrReqDto objectAttrReqDto) {
//        List<ItemInfoRespDto> result = forecastDataService.list(objectAttrReqDto);
//        return Result.ok(result);
//    }

    @ApiOperation(value = "批量插入预报数据数据", notes = "批量插入预报数据数据")
    @PostMapping("/insertOrUpdateBatch")
    Result<Integer> insertOrUpdateBatch(@RequestBody List<ForecastDataEntity> forecastDataEntities);

}
