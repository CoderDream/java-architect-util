package com.debug.middleware.server.dto;/**
 * Created by Administrator on 2019/4/7.
 */

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录实体信息
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/7 19:05
 **/
@Data
@ToString
public class UserLoginDto implements Serializable{
    @NotBlank
    private String userName;//用户名-必填
    @NotBlank
    private String password;//登录密码-必填

    private Integer userId;//用户id
}