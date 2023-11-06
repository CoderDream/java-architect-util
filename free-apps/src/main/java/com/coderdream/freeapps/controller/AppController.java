package com.coderdream.freeapps.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

import java.util.stream.Collectors;
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

    @PostMapping("/selectPage")
    Result<IPage<AppEntity>> selectPage(@RequestBody Page<AppEntity> page) {
        // IPage<AppEntity> selectPage(Page<AppEntity> page);
//        Page<AppEntity> page = new Page<>();

        IPage<AppEntity> result = appService.selectPage(page);
        return Result.ok(result);
    }

    @GetMapping("/selectPageAppId")
    Result<List<String>> selectPageAppId(String current, String size) {
        if (current == null || StrUtil.isEmpty(current)) {
            current = "1";
        }
        if (size == null || StrUtil.isEmpty(size)) {
            size = "10";
        }
        Page<AppEntity> page = new Page<>();
        page.setCurrent(Long.parseLong(current));
        page.setSize(Long.parseLong(size));
        IPage<AppEntity> appEntityIPage = appService.selectPage(page);
        List<String> result = appEntityIPage.getRecords().stream().map(AppEntity::getAppId)
            .collect(Collectors.toList());

        return Result.ok(result);
    }

    @PostMapping("/listAppId")
    Result<List<String>> listAppId(@RequestBody AppEntity app) {
        // IPage<AppEntity> selectPage(Page<AppEntity> page);
//        Page<AppEntity> page = new Page<>();

        List<AppEntity> appEntityList = appService.selectList(app);
        List<String> appIdList = appEntityList.stream().map(AppEntity::getAppId).collect(Collectors.toList());

        return Result.ok(appIdList);
    }

    @GetMapping("/listAppId10")
    Result<List<String>> listAppId10() {
        // IPage<AppEntity> selectPage(Page<AppEntity> page);
//        Page<AppEntity> page = new Page<>();
        //@PathVariable AppEntity app
        List<AppEntity> appEntityList = appService.selectList(new AppEntity());
        List<String> appIdList = appEntityList.stream().map(AppEntity::getAppId).collect(Collectors.toList());

        return Result.ok(appIdList);
    }

    @GetMapping("/listAppIds")
    Result<List<String>> listAppIds(String size) {
        if (size == null || StrUtil.isEmpty(size)) {
            size = "10";
        }
        List<AppEntity> appEntityList = appService.selectListBySize(size);
        List<String> appIdList = appEntityList.stream().map(AppEntity::getAppId).collect(Collectors.toList());

        return Result.ok(appIdList);
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
        if (!CollectionUtils.isEmpty(appIdList)) {
            for (String appId : appIdList) {
                app = new AppEntity();
                app.setAppId(appId);
                app.setCreatedDate(new Date());
                app.setDeleteFlag(0);
                appList.add(app);
            }
        }
        int result = appService.insertOrUpdateBatch(appList);
        return Result.ok(result);
    }
}
