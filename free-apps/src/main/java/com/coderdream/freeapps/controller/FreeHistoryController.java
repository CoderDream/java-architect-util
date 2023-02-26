package com.coderdream.freeapps.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.common.entity.PageResult;
import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "")
@RequestMapping("/freeHistorys")
@RequiredArgsConstructor
public class FreeHistoryController {

  private final FreeHistoryService freeHistoryService;

  @GetMapping("/queryPage")
  @Operation(description = "获取分页列表")
  public Result<PageResult<FreeHistoryVO>> queryPage(@RequestBody FreeHistoryQueryPageDTO dto) {
    IPage<FreeHistoryVO> freeHistoryVOPage = freeHistoryService.queryPage(dto);
    return Result.ok(PageResult.ok(freeHistoryVOPage));
  }

  @GetMapping
  @Operation(description = "获取列表")
  public Result<List<FreeHistoryVO>> queryList(@RequestBody FreeHistoryDTO dto) {
    List<FreeHistoryVO> freeHistoryVOList = freeHistoryService.queryList(dto);
    return Result.ok(freeHistoryVOList);
  }

  @GetMapping("/{id}")
  @Operation(description = "获取详情")
  public Result<FreeHistoryVO> get(@PathVariable("id") Long id) {
    FreeHistoryVO freeHistoryVO = freeHistoryService.get(id);
    return Result.ok(freeHistoryVO);
  }

  @PostMapping
  @Operation(description = "新增")
  public Result<Object> add(@RequestBody FreeHistoryDTO dto) {
    boolean flag = freeHistoryService.add(dto);
    if (!flag) {
      return Result.failed();
    }
    return Result.ok();
  }

  @PutMapping
  @Operation(description = "编辑")
  public Result<Object> edit(@RequestBody FreeHistoryDTO dto) {
    boolean flag = freeHistoryService.edit(dto);
    if (!flag) {
      return Result.failed();
    }
    return Result.ok();
  }

  @DeleteMapping
  @Operation(description = "删除")
  public Result<Object> delete(@RequestParam String id) {
    boolean flag = freeHistoryService.delete(id);
    if (!flag) {
      return Result.failed();
    }
    return Result.ok();
  }
}
