package com.coderdream.freeapps.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.common.entity.PageResult;
import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.vo.AppVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Tag(name = "应用管理")
@Api(tags = "应用管理")
@RequestMapping("/apps")
@RestController
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @PostMapping("/queryPage")
    @ApiOperation(value = "获取分页列表", notes = "获取分页列表")
    @Operation(description = "获取分页列表")
    public Result<PageResult<AppVO>> queryPage(@RequestBody AppQueryPageDTO dto) {
        IPage<AppVO> appVOPage = appService.queryPage(dto);
        return Result.ok(PageResult.ok(appVOPage));
    }

    @GetMapping
    @Operation(description = "获取列表")
    public Result<List<AppVO>> queryList(@RequestBody AppDTO dto) {
        List<AppVO> appVOList = appService.queryList(dto);
        return Result.ok(appVOList);
    }

    @GetMapping("/{id}")
    @Operation(description = "获取详情")
    public Result<AppVO> get(@PathVariable("id") Long id) {
        AppVO appVO = appService.get(id);
        return Result.ok(appVO);
    }

    @PostMapping
    @Operation(description = "新增")
    public Result<Object> add(@RequestBody AppDTO dto) {
        boolean flag = appService.add(dto);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @PutMapping
    @Operation(description = "编辑")
    public Result<Object> edit(@RequestBody AppDTO dto) {
        boolean flag = appService.edit(dto);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @DeleteMapping
    @Operation(description = "删除")
    public Result<Object> delete(@RequestParam String id) {
        boolean flag = appService.delete(id);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @PostMapping("/selectList")
    Result<List<AppEntity>> selectList(@RequestBody AppEntity app) {
        List<AppEntity> result = appService.selectList(app);
        return Result.ok(result);
    }
    @PostMapping("/insertSelective")
    Result<Integer> insertSelective(@RequestBody AppEntity app) {
        int result = appService.insertSelective(app);
        return Result.ok(result);
    }

    @PostMapping("/insertOrUpdateBatch")
    Result<Integer> insertOrUpdateBatch(@RequestBody List<AppEntity> appList) {
        int result = appService.insertOrUpdateBatch(appList);
        return Result.ok(result);
    }

    @PostMapping("/batchCreate")
    Result<Integer> batchCreate(@RequestBody List<String> appIdList) {
        List<AppEntity> appList = new ArrayList<>();
        AppEntity app;
        if(!CollectionUtils.isEmpty(appIdList)) {
            for (String appId: appIdList) {
                app = new AppEntity();
                app.setAppId(appId);
                app.setCreatedDate(new Date());
                app.setDelFlag(0);
                appList.add(app);
            }
        }
        int result = appService.insertOrUpdateBatch(appList);
        return Result.ok(result);
    }
}
