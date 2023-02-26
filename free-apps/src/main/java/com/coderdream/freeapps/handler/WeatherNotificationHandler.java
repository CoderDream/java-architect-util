package com.coderdream.freeapps.handler;

import com.coderdream.freeapps.service.UserService;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/27 4:28 下午
 */
@Component
@RequiredArgsConstructor
public class WeatherNotificationHandler extends IJobHandler {

  private final UserService userService;

  @XxlJob(value = "weatherNotificationHandler")
  @Override
  public void execute() throws Exception {
    userService.pushWeatherNotification();
  }
}
