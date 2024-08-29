package com.coderdream.demos.web.service;

import com.coderdream.demos.web.pojo.BossJobDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_boss_job_detail】的数据库操作Service
* @createDate 2024-07-31 23:50:42
*/
@Service
public interface BossJobDetailService extends IService<BossJobDetail> {
    int insertOrUpdateBatch(@Param("entities") List<BossJobDetail> entities);
}
