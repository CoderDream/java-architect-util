package com.coderdream.easyexcelpractise.client;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.easyexcelpractise.Result;
import com.keepsoft.microservice.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
 * url: url一般用于调试，可以手动指定@FeignClient调用的地址
 * path: 定义当前FeignClient的统一前缀 http://172.16.104.131:30417/
 */
//@FeignClient(name = "keepsoft-standardized-object-service-object-type-meta", path = "api/object/data/object/attr", url = "http://172.16.104.131:30417")
@FeignClient(name = "keepsoft-standardized-object-service-object-type-meta",
        path = "api/standardized/object/object-type-meta",
        contextId = "api/standardized/object/object-type-meta",
        url = "http://127.0.0.1:9085")
public interface ObjectTypeMetaServiceClient {

    /**
     * 获取对象类型元数据列表
     *
     * @return 列表
     */
    @ApiOperation(value = "获取对象类型元数据列表-分页列表查询", notes = "获取对象类型元数据列表-分页列表查询")
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
    @ApiOperation(value = "获取对象类型元数据属性列表-分页列表查询", notes = "获取对象类型元数据属性列表-分页列表查询")
    @PostMapping("/listAttrs")
    Result<Page<AttrMetaRespDto>> listAttrs(@RequestBody AttrMetaReqDto attrMetaReqDto);

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
     * 修改对象类型元数据
     *
     * @param objectTypeMetaReqDto 对象类型元数据信息
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改对象类型元数据", notes = "修改对象类型元数据")
    public Result update(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);


    @PostMapping("delete")
    @ApiOperation(value = "删除对象类型元数据", notes = "删除对象类型元数据")
    public Result delete(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    @PostMapping("deleteInBatches")
    @ApiOperation(value = "批量删除对象类型元数据", notes = "批量删除对象类型元数据")
    public Result deleteInBatches(@RequestBody List<ObjectTypeMetaReqDto> objectTypeMetaReqDtoList);

    /**
     * 生成最新编码
     *
     * @return 列表
     */
    @ApiOperation(value = "生成最新编码", notes = "生成最新编码")
    @PostMapping("/genCode")
    Result<String> genCode(@RequestBody AttrMetaReqDto attrMetaReqDto);

    @ApiOperation(value = "生成图列", notes = "生成图列")
    @PostMapping("/getGraph")
    public Result getGraph(@RequestBody Map<String, String> map);

    /**
     * 获取流域对象信息列表
     *
     * @return
     */
    @ApiOperation(value = "获取流域对象信息列表", notes = "获取流域对象信息列表")
    @PostMapping("/listAreaObject")
    Result<List<ObjectTypeInfoRespDto>> listAreaObject(@RequestBody ObjectMetaReqDto objectMetaReqDto);

    /**
     * 获取对象列表-排除流域
     *
     * @return
     */
    @ApiOperation(value = "获取对象信息列表-排除流域", notes = "获取对象列表-排除流域")
    @PostMapping("/listObjectWithoutArea")
    Result<List<ObjectTypeInfoRespDto>> listObjectWithoutArea(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    /**
     * 获取对象列表-包含地理信息
     *
     * @return
     */
    @ApiOperation(value = "获取对象信息列表-包含地理信息", notes = "获取对象列表-包含地理信息")
    @PostMapping("/listObjectGeoInfo")
    Result<List<ObjectTypeInfoRespDto>> listObjectGeoInfo(@RequestBody ObjectTypeMetaReqDto objectTypeMetaReqDto);

    /**
     * 获取对象列表（无地理信息）-排除流域
     *
     * @return
     */
    @ApiOperation(value = "获取对象信息列表（无地理信息）-排除流域", notes = "获取对象列表（无地理信息）-排除流域")
    @PostMapping("/listObjectNoGeoWithoutArea")
    Result<List<ObjectTypeInfoRespDto>> listObjectNoGeoWithoutArea(@RequestBody ObjectMetaReqDto objectMetaReqDto);

    /**
     * 获取对象类型元数据列表-排除流域
     *
     * @return
     */
    @ApiOperation(value = "获取对象类型元数据列表-排除流域", notes = "获取对象类型元数据列表-排除流域")
    @PostMapping("/listWithoutArea")
    Result<JSONArray> listWithoutArea();

    /**
     * 对象结构导入（对象类型，属性类型和属性（基础属性、数据属性），实例）。
     *
     * @param file     对象结构文件
     * @param userName 用户名
     */
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "对象结构导入", notes = "对象结构导入")
    public Result upload(@ApiParam("对象结构文件") MultipartFile file, String userName);

    /**
     * 地理信息文件上传
     *
     * @param files     文件列表
     * @param geoReqDto 地理信息请求
     */
    @PostMapping(value = "/uploadGeo", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "地理信息文件上传", notes = "地理信息文件上传")
    public Result uploadGeo(@ApiParam("地理文件集合") MultipartFile[] files,
                            @ApiParam("地理信息请求") GeoReqDto geoReqDto);

    /**
     * 预览地理信息文件
     *
     * @param files   文件列表
     * @param charset 字符集
     */
    @PostMapping(value = "/previewGeo", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "预览地理信息文件", notes = "预览地理信息文件")
    public ResponseEntity<FileSystemResource> previewGeo(@ApiParam("地理文件集合") MultipartFile[] files, @ApiParam("字符集") String charset);

    @PostMapping(value = "/exportAllTypeExcel")
    @ApiOperation(value = "导出所有的表的数据")
    public void exportAllTypeExcel(HttpServletResponse response) throws Exception;

}

