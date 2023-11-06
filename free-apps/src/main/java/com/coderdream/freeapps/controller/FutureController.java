package com.coderdream.freeapps.controller;

import com.coderdream.freeapps.service.AsyncInvokeService;
import com.coderdream.freeapps.service.impl.AsyncInvokeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author CoderDream
 */
@Slf4j
@Tag(name = "后台管理")
@Api(tags = "后台管理")
@RequestMapping("/future")
@RestController
@RequiredArgsConstructor
public class FutureController {

    @Resource
    private AsyncInvokeServiceImpl asyncInvokeService;

    @GetMapping("/test")
    public String b() throws InterruptedException, ExecutionException {
        Future<Boolean> future1 = asyncInvokeService.exec1("张三");
        Future<Boolean> future2 = asyncInvokeService.exec2("15618881888");

        List<Future<Boolean>> futureList = new ArrayList<>();
        futureList.add(future1);
        futureList.add(future2);

        List list = new ArrayList();
        int i = 0;

        //查询任务执行的结果
        for (Future<?> future : futureList) {
            while (true) {//CPU高速轮询：每个future都并发轮循，判断完成状态然后获取结果，这一行，是本实现方案的精髓所在。即有10个future在高速轮询，完成一个future的获取结果，就关闭一个轮询
                if (future.isDone()
                    && !future.isCancelled()) { //获取future成功完成状态，如果想要限制每个任务的超时时间，取消本行的状态判断+future.get(1000*1, TimeUnit.MILLISECONDS)+catch超时异常使用即可。
                    Object o = future.get();
                    Boolean result = (Boolean) o;//获取结果
                    i++;
                    System.out.println("任务i=" + i + "获取完成!" + new Date());
                    list.add(result);
                    break;//当前future获取结果完毕，跳出while
                } else {
                    Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
                }
            }
        }
        return "执行成功！！";
    }

}
