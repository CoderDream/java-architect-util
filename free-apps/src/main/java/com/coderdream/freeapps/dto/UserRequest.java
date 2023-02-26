package com.coderdream.freeapps.dto;

import lombok.Data;

/**
 * @author hresh
 * @date 2021/9/4 9:32
 * @description 表单输入信息，包括用户和密码
 */
@Data
public class UserRequest {

  private String username;
  private String password;
  private String phone;

}
