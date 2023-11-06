package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.model.AdminEntity;
import com.coderdream.freeapps.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author CoderDream
 */
@Slf4j
@Tag(name = "后台管理")
@Api(tags = "后台管理")
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    Result<AdminEntity> login(@RequestBody AdminEntity adminEntity) {
        AdminEntity result = adminService.login(adminEntity);
        if(result == null) {
            Result.failed("用户名密码无效");
        }
        return Result.ok(result);
    }
}
