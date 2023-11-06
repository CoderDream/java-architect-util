package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.AppIcon;
import com.coderdream.freeapps.model.JobInfoEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_job_info(job_info)】的数据库操作Service
* @createDate 2023-06-02 13:42:50
*/
public interface JobInfoService extends IService<JobInfoEntity> {

    int insertOrUpdateBatch(List<JobInfoEntity> jobInfoEntityList);

    int updateColumns();
}
