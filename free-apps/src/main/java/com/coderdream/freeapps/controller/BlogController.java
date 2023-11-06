package com.coderdream.freeapps.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.model.BlogEntity;
import com.coderdream.freeapps.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "博客管理")
@Api(tags = "博客管理")
@RequestMapping("/blog")
@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

//    @PostMapping("/list")
//    Result<List<BlogEntity>> list(@RequestBody BlogEntity blogEntity) {
//        List<BlogEntity> result = blogService.list(blogEntity);
//        return Result.ok(result);
//    }

    @GetMapping("/list")
    @Operation(description = "获取列表")
//    @PostMapping("/list")
    public Result<List<BlogEntity>> list(BlogEntity blogEntity) {
        List<BlogEntity> blogEntityList = blogService.list(blogEntity);
        return Result.ok(blogEntityList);
    }

    // PageVO pageVO
    @GetMapping("/search")
    @Operation(description = "获取列表")
    public Result<Page<BlogEntity>> search(String keyword, String categoryId, Integer currentPage, Integer pageSize) {
        if(currentPage == null) {
            currentPage = 1;
        }
        if(pageSize == null) {
            pageSize = 10;
        }
        Page page = new Page<>(currentPage, pageSize);
        QueryWrapper<BlogEntity> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isEmpty(categoryId)) {
            log.error("categoryId 为空");
        } else {
            queryWrapper.eq("category_id", categoryId);
        }
        if(StrUtil.isNotEmpty(keyword)) {
            queryWrapper.like("title", keyword);
        }
        Page<BlogEntity> blogEntityList = blogService.page(page, queryWrapper);
        return Result.ok(blogEntityList);
    }

    @GetMapping("/detail")
    @Operation(description = "获取详情") // @PathVariable("id")
    public Result<BlogEntity> get(String id) {
        if(id != null) {
            log.info(id +"id");
        }
        BlogEntity blogEntity = blogService.getById(id); // Long.parseLong(id)
        return Result.ok(blogEntity);
    }

    @PostMapping("/add")
    @Operation(description = "新增")
    public Result<Object> add(@RequestBody BlogEntity blogEntity) {
        boolean flag = blogService.save(blogEntity);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @PutMapping
    @Operation(description = "编辑")
    public Result<Object> edit(@RequestBody BlogEntity blogEntity) {
        boolean flag = blogService.updateById(blogEntity);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @DeleteMapping
    @Operation(description = "删除")
    public Result<Object> delete(@RequestParam String id) {
        boolean flag = blogService.removeById(id);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }
}
