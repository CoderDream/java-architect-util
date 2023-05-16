package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.common.entity.PageResult;
import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.service.SyncTaskService;
import com.coderdream.freeapps.vo.AppVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "同步任务管理")
@Api(tags = "同步任务管理")
@RequestMapping("/sync/task")
@RestController
@RequiredArgsConstructor
public class SyncTaskController {
    @Resource
    private SyncTaskService snapshotService;

    @PostMapping("/dailyProcess")
    @ApiOperation(value = "同步任务", notes = "同步任务")
    @Operation(description = "同步任务")
    public Result<PageResult<AppVO>> dailyProcessCl(@RequestBody SyncTaskReqDto syncTaskReqDto) {
        snapshotService.dailyProcess(syncTaskReqDto);
        return Result.ok();
    }

}
