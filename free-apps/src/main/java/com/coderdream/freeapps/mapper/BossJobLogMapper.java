package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.BossJobLogEntity;

import com.coderdream.freeapps.model.JobInfoEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_boss_job_log(t_boss_job_log)】的数据库操作Mapper
* @createDate 2023-06-08 08:53:30
* @Entity com.coderdream.freeapps.model.BossJobLogEntity
*/
public interface BossJobLogMapper extends BaseMapper<BossJobLogEntity> {

    int insertOrUpdateBatch(List<BossJobLogEntity> entities);
}




