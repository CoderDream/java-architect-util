package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author hresh
 * @date 2021/5/4 21:13
 * @description
 */
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  private String username;
  private String password;
  private String phone;

  @TableField(value = "created_date", fill = FieldFill.INSERT)
  @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private LocalDateTime createdDate;

  @TableField(value = "last_modified_date", fill = FieldFill.INSERT_UPDATE)
  @DateTimeFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
  )
  private LocalDateTime lastModifiedDate;

}
