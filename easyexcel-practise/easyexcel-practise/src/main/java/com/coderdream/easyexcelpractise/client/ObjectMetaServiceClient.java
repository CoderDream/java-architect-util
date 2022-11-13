package com.coderdream.easyexcelpractise.client;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.dto.AttrMetaReqDto;
import com.coderdream.easyexcelpractise.dto.os.ObjectMetaReqDto;
import com.coderdream.easyexcelpractise.dto.os.ObjectMetaRespDto;
import com.coderdream.easyexcelpractise.entity.ObjectMetaEntity;
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
//@FeignClient(name = "keepsoft-standardized-object-service-object-meta", path = "api/object/data/object/attr", url = "http://172.16.104.131:30417")
@FeignClient(name = "keepsoft-standardized-object-service-object-meta", path = "api/standardized/object/object-meta", url = "http://127.0.0.1:9085")
public interface ObjectMetaServiceClient {

    /**
     * 调用接口返回数据
     *
     * @return
     */
    @PostMapping("/list")
    Result<Page<ObjectMetaRespDto>> list(@RequestBody ObjectMetaReqDto objectTypeMetaReqDto);

    /**
     * 获取对象元数据
     *
     * @return 获取对象元数据
     */
    @ApiOperation(value = "获取对象元数据", notes = "获取对象元数据")
    @PostMapping("/selectOne")
    Result<ObjectMetaRespDto> selectOne(@RequestBody ObjectMetaReqDto objectMetaReqDto);

    /**
     * 新增对象类型元数据
     *
     * @param objectTypeMetaReqDto 对象类型元数据信息
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增对象类型元数据", notes = "新增对象类型元数据")
    public Result add(@RequestBody ObjectMetaReqDto objectMetaReqDto);

    @ApiOperation(value = "批量插入对象元数据", notes = "批量插入对象元数据")
    @PostMapping("/insertOrUpdateBatch")
    Result<Integer> insertOrUpdateBatch(@RequestBody List<ObjectMetaEntity> entities);

    /**
     * 生成最新编码
     *
     * @return 列表
     */
    @ApiOperation(value = "生成最新编码", notes = "生成最新编码")
    @PostMapping("/genCode")
    Result<String> genCode(@RequestBody AttrMetaReqDto attrMetaReqDto);

}
