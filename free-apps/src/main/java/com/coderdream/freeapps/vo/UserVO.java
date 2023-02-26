package com.coderdream.freeapps.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVO {

  private String username;
  private String password;
  private String phone;
}
