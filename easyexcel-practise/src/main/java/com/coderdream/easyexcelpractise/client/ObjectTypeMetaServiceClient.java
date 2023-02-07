package com.coderdream.easyexcelpractise.client;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.easyexcelpractise.Result;
import com.coderdream.easyexcelpractise.dto.AttrMetaReqDto;
import com.coderdream.easyexcelpractise.dto.AttrMetaRespDto;
import com.coderdream.easyexcelpractise.dto.ObjectTypeMetaReqDto;
import com.coderdream.easyexcelpractise.dto.ObjectTypeMetaRespDto;
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
//@FeignClient(name = "keepsoft-standardized-object-service-object-type-meta", path = "api/object/data/object/attr", url = "http://172.16.104.131:30417")
@FeignClient(name = "keepsoft-standardized-object-service-object-type-meta", path = "api/standardized/object/object-type-meta", url = "http://127.0.0.1:9085")
public interface ObjectTypeMetaServiceClient {

    /**
     * 调用接口返回数据
     *
     * @return
     */
    @PostMapping("/list")
    Result<Page<ObjectTypeMetaRespDto>> list(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    /**
     * 获取对象类型元数据
     *
     * @return 获取对象类型元数据
     */
    @ApiOperation(value = "获取对象类型元数据", notes = "获取对象类型元数据")
    @PostMapping("/selectOne")
    Result<ObjectTypeMetaRespDto> selectOne(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    /**
     * 获取对象类型元数据属性列表
     *
     * @return 列表
     */
    @ApiOperation(value = "获取对象类型元数据属性列表", notes = "获取对象类型元数据属性列表")
    @PostMapping("/listAllAttrs")
    Result<List<AttrMetaRespDto>> listAllAttrs(@RequestBody AttrMetaReqDto attrMetaReqDto);

    /**
     * 新增对象类型元数据
     *
     * @param objectTypeMetaReqDto 对象类型元数据信息
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增对象类型元数据", notes = "新增对象类型元数据")
    public Result add(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    /**
     * 生成最新编码
     *
     * @return 列表
     */
    @ApiOperation(value = "生成最新编码", notes = "生成最新编码")
    @PostMapping("/genCode")
    Result<String> genCode(@RequestBody AttrMetaReqDto attrMetaReqDto);

}
