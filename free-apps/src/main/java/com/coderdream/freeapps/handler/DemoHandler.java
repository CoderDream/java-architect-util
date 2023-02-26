package com.coderdream.freeapps.handler;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/26 8:15 下午
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DemoHandler extends IJobHandler {

  @XxlJob(value = "demoHandler")
  @Override
  public void execute() throws Exception {
    log.info("自动任务" + this.getClass().getSimpleName() + "执行");
  }
}
