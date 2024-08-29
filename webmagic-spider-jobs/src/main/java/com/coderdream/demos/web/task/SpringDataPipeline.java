package com.coderdream.demos.web.task;


import com.coderdream.demos.web.pojo.JobInfoEntity;
import com.coderdream.demos.web.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @ClassName SpringDataPipeline
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/6 4:52
 * @Version 1.0
 */
@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情对象
        JobInfoEntity jobInfo = resultItems.get("jobInfo");

        //判断数据是否不为空
        if (jobInfo != null) {
            //如果不为空把数据保存到数据库中
            this.jobInfoService.save(jobInfo);
        }
    }
}
