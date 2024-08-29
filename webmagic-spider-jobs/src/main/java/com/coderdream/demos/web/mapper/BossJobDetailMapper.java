package com.coderdream.demos.web.mapper;

import com.coderdream.demos.web.pojo.BossJobDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author CoderDream
* @description 针对表【t_boss_job_detail】的数据库操作Mapper
* @createDate 2024-07-31 23:50:42
* @Entity com.coderdream.demos.web.pojo.BossJobDetail
*/
@Mapper
public interface BossJobDetailMapper extends BaseMapper<BossJobDetail> {
    int insertOrUpdateBatch(@Param("entities") List<BossJobDetail> entities);
}




