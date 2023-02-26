package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.common.entity.Result;
import com.coderdream.freeapps.dto.UserRequest;
import com.coderdream.freeapps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 10:02 下午
 */
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public Result<Object> register(@RequestBody UserRequest userRequest) {
    userService.register(userRequest);
    return Result.ok();
  }

}
