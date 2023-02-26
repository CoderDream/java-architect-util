package com.coderdream.freeapps.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

  @Schema(name = "")
  private String username;

  @Schema(name = "")
  private String password;

  @Schema(name = "")
  private String phone;

}
