package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.model.CategoryEntity;
import com.coderdream.freeapps.service.CategoryService;
import com.coderdream.freeapps.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "类型管理")
@Api(tags = "类型管理")
@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

//    @PostMapping("/list")
//    Result<List<CategoryEntity>> list(@RequestBody CategoryEntity categoryEntity) {
//        List<CategoryEntity> result = categoryService.list(categoryEntity);
//        return Result.ok(result);
//    }

    @GetMapping("/list")
    @Operation(description = "获取列表")
//    @PostMapping("/list")
    public Result<List<CategoryEntity>> list(CategoryEntity categoryEntity) {
        List<CategoryEntity> categoryEntityList = categoryService.list(categoryEntity);
        return Result.ok(categoryEntityList);
    }

    // PageVO pageVO
    @GetMapping("/all")
    @Operation(description = "获取列表")
//    @PostMapping("/list")
    public Result<List<CategoryEntity>> all(PageVO pageVO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        List<CategoryEntity> categoryEntityList = categoryService.list(categoryEntity);
        return Result.ok(categoryEntityList);
    }


    @GetMapping("/{id}")
    @Operation(description = "获取详情")
    public Result<CategoryEntity> get(@PathVariable("id") Long id) {
        CategoryEntity categoryEntity = categoryService.getById(id);
        return Result.ok(categoryEntity);
    }

    @PostMapping("/add")
    @Operation(description = "新增")
    public Result<Object> add(@RequestBody CategoryEntity categoryEntity) {
        boolean flag = categoryService.save(categoryEntity);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @PutMapping
    @Operation(description = "编辑")
    public Result<Object> edit(@RequestBody CategoryEntity categoryEntity) {
        boolean flag = categoryService.updateById(categoryEntity);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }

    @DeleteMapping
    @Operation(description = "删除")
    public Result<Object> delete(@RequestParam String id) {
        boolean flag = categoryService.removeById(id);
        if (!flag) {
            return Result.failed();
        }
        return Result.ok();
    }
}
