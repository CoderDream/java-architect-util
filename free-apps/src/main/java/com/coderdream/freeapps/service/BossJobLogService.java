package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.BossJobLogEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_boss_job_log(t_boss_job_log)】的数据库操作Service
* @createDate 2023-06-08 08:53:30
*/
public interface BossJobLogService extends IService<BossJobLogEntity> {
    int insertOrUpdateBatch(List<BossJobLogEntity> bossJobLogEntityList);
    int updateColumns();
}
