package com.coderdream.freeapps.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 5:33 下午
 */
@Data
@Builder
public class NotificationInfo {

  private String message;

  private WeatherInfo weatherInfo;

}
