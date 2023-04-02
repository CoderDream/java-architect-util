package com.coderdream.freeapps.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.common.entity.PageResult;
import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.vo.AppVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "描述管理")
@Api(tags = "描述管理")
@RequestMapping("/description")
@RestController
@RequiredArgsConstructor
public class DescriptionController {

    private final DescriptionService descriptionService;

//    @PostMapping("/queryPage")
//    @ApiOperation(value = "获取分页列表", notes = "获取分页列表")
//    @Operation(description = "获取分页列表")
//    public Result<PageResult<AppVO>> queryPage(@RequestBody AppQueryPageDTO dto) {
//        IPage<AppVO> appVOPage = descriptionService.queryPage(dto);
//        return Result.ok(PageResult.ok(appVOPage));
//    }
//
//    @GetMapping
//    @Operation(description = "获取列表")
//    public Result<List<AppVO>> queryList(@RequestBody AppDTO dto) {
//        List<AppVO> appVOList = descriptionService.queryList(dto);
//        return Result.ok(appVOList);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(description = "获取详情")
//    public Result<AppVO> get(@PathVariable("id") Long id) {
//        AppVO appVO = descriptionService.get(id);
//        return Result.ok(appVO);
//    }
//
//    @PostMapping
//    @Operation(description = "新增")
//    public Result<Object> add(@RequestBody AppDTO dto) {
//        boolean flag = descriptionService.add(dto);
//        if (!flag) {
//            return Result.failed();
//        }
//        return Result.ok();
//    }
//
//    @PutMapping
//    @Operation(description = "编辑")
//    public Result<Object> edit(@RequestBody AppDTO dto) {
//        boolean flag = descriptionService.edit(dto);
//        if (!flag) {
//            return Result.failed();
//        }
//        return Result.ok();
//    }
//
//    @DeleteMapping
//    @Operation(description = "删除")
//    public Result<Object> delete(@RequestParam String id) {
//        boolean flag = descriptionService.delete(id);
//        if (!flag) {
//            return Result.failed();
//        }
//        return Result.ok();
//    }
//
//    @PostMapping("/selectList")
//    Result<List<App>> selectList(@RequestBody App app) {
//        List<App> result = descriptionService.selectList(app);
//        return Result.ok(result);
//    }
//    @PostMapping("/insertSelective")
//    Result<Integer> insertSelective(@RequestBody App app) {
//        int result = descriptionService.insertSelective(app);
//        return Result.ok(result);
//    }

    @PostMapping("/insertOrUpdateBatch")
    Result<Integer> insertOrUpdateBatch(@RequestBody List<Description> descriptionList) {
        int result = descriptionService.insertOrUpdateBatch(descriptionList);
        return Result.ok(result);
    }


    @GetMapping("export-xls-xsk")
    public void exportExcelXsk(HttpServletResponse response) throws IOException, ClassNotFoundException {
        ExcelWriter writer = ExcelUtil.getWriter();
        List<Description> baseInfoRespDto = descriptionService.list(null);

        List<Map<String, Object>> rows = baseInfoRespDto.stream().map(item -> {
            Map<String, Object> maps = new HashMap<>();
            maps.put("rs_id", item.getAppId());
            maps.put("res_code", item.getDescriptionCl());
            maps.put("res_name", item.getDescriptionCn());
            maps.put("low_left_lon", item.getDescriptionMy());
            maps.put("low_left_lat", item.getDescriptionZm());
            maps.put("res_type", item.getDescriptionUs());
            return maps;
        }).collect(Collectors.toList());

        // Title
        int columns = Class.forName("com.keepsoft.microservice.dto.api.BaseInfoStbprpRespDto").getDeclaredFields().length;
        writer.merge(columns - 1, "新水库");

        // Header
        writer.addHeaderAlias("rs_id", "新系统水库id");
        writer.addHeaderAlias("res_code", "水库编码");
        writer.addHeaderAlias("res_name", "水库名称");
        writer.addHeaderAlias("low_left_lon", "左下角经度");
        writer.addHeaderAlias("low_left_lat", "左下角纬度");
        writer.addHeaderAlias("res_type", "水库类型");

        // Body
        writer.setColumnWidth(0, 30);
        writer.setColumnWidth(1, 30);
        writer.setColumnWidth(2, 30);
        writer.setColumnWidth(3, 30);
        writer.setColumnWidth(4, 30);
        writer.write(rows, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("新水库系统水库基本信息-" + DateUtil.today() + ".xls", "utf-8"));

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

//    @PostMapping("/batchCreate")
//    Result<Integer> batchCreate(@RequestBody List<String> appIdList) {
//        List<App> appList = new ArrayList<>();
//        App app;
//        if(!CollectionUtils.isEmpty(appIdList)) {
//            for (String appId: appIdList) {
//                app = new App();
//                app.setAppId(appId);
//                app.setCreatedDate(new Date());
//                app.setDelFlag(0);
//                appList.add(app);
//            }
//        }
//        int result = descriptionService.insertOrUpdateBatch(appList);
//        return Result.ok(result);
//    }
}
