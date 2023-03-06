package com.coderdream.freeapps.handler;

import com.coderdream.freeapps.service.UserService;
import com.coderdream.freeapps.util.XxlUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/26 11:53 上午
 */
@Component
@RequiredArgsConstructor
public class SayHelloHandler {

    private final UserService userService;
    private final XxlUtil xxlUtil;

    @XxlJob(value = "sayHelloHandler")
    public void execute() {
        String param = XxlJobHelper.getJobParam();
        userService.sayHelloToUser(param);

        long jobId = XxlJobHelper.getJobId();
        xxlUtil.removeJob(jobId);
    }
}
