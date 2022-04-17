package com.debug.middleware.server.event;/**
 * Created by Administrator on 2019/3/29.
 */

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 用户登录成功后的事件实体
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/29 16:00
 **/
@Data
@ToString
public class LoginEvent extends ApplicationEvent implements Serializable{

    private String userName; //用户名
    private String loginTime; //登录时间
    private String ip; //所在ip

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String userName, String loginTime, String ip) {
        super(source);
        this.userName = userName;
        this.loginTime = loginTime;
        this.ip = ip;
    }
}