package com.coderdream.freeapps.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/26 11:48 上午
 */
@Configuration
public class XxlJobConfig {

  @Value("${xxl.job.admin.addresses}")
  private String adminAddresses;
  @Value("${xxl.job.executor.appname}")
  private String appName;
  @Value("${xxl.job.executor.ip}")
  private String ip;
  @Value("${xxl.job.executor.port}")
  private int port;
  @Value("${xxl.job.accessToken}")
  private String accessToken;
  @Value("${xxl.job.executor.logpath}")
  private String logPath;
  @Value("${xxl.job.executor.logretentiondays}")
  private int logRetentionDays;

  @Bean
  public XxlJobSpringExecutor xxlJobExecutor() {
    // 创建 XxlJobSpringExecutor 执行器
    XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
    xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
    xxlJobSpringExecutor.setAppname(appName);
    xxlJobSpringExecutor.setIp(ip);
    xxlJobSpringExecutor.setPort(port);
    xxlJobSpringExecutor.setAccessToken(accessToken);
    xxlJobSpringExecutor.setLogPath(logPath);
    xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
    // 返回
    return xxlJobSpringExecutor;
  }
}
