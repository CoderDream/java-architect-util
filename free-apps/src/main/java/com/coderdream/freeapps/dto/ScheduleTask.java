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
 * @date 2022/11/23 2:28 下午
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleTask {

  // 任务名
  private String jobName;

  private String jobDesc;

  private String author;

  // 任务组
  private String groupName;

  // 任务数据
  private String jobData;

  // 任务执行处理类，小写字母开头
  private String jobHandlerClass;

  // 任务执行时间
  private Long jobTime;

  // 任务执行时间，cron时间表达式 （如：0/5 * * * * ? ）
  private String jobCronTime;

  // 任务执行次数，（<0:表示不限次数）
  private int jobTimes;

}
