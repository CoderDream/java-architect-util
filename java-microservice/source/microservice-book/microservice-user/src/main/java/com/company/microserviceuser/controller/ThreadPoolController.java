package com.company.microserviceuser.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.company.microserviceuser.service.IThreadPoolService;
import com.company.microserviceuser.vo.common.*;
import java.util.List;
import java.util.Map;

/**
 * Thread pool test.
 * 线程池测试接口
 * @author xindaqi
 * @since 2020-10-26
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/threadpool")
@Api(tags = "线程池测试")
public class ThreadPoolController {

    // 线程池处理服务自动装配
    @Autowired 
    private IThreadPoolService threadPoolService;

    /**
     * 利用线程池处理列表数据
     * @param params
     * @return
     */
    @PostMapping(value = "/calculate")
    @ApiOperation("数据批处理接口")
    public String threadPoolTest(@RequestBody Map<String, List<Integer>> params) {
        
        List<Integer> data = params.get("data");
        // 创建三个任务
        for (int i = 0; i < 3; i++) {
            // 调用线程池：数据批量处理
            threadPoolService.dataProcessBatch(data);
        }
        return "success";

    }

}