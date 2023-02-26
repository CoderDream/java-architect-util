package com.coderdream.freeapps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/24 10:58 下午
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {

  // 任务名
  private String jobName;

  // 任务组
  private String groupName;

  // 任务数据
  private String jobData;

  private String triggerKey;

  private String jobStatus;

  // 任务执行时间，cron时间表达式 （如：0/5 * * * * ? ）
  private String jobCronTime;

}
