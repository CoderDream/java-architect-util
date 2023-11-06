package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.BlogEntity;
import com.coderdream.freeapps.model.JobInfoEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_job_info(job_info)】的数据库操作Mapper
* @createDate 2023-06-02 13:42:50
* @Entity com.coderdream.freeapps.model.JobInfoEntity
*/
public interface JobInfoMapper extends BaseMapper<JobInfoEntity> {

    int insertOrUpdateBatch(List<JobInfoEntity> entities);
}




