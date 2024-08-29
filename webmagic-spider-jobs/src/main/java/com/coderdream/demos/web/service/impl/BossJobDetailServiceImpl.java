package com.coderdream.demos.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.demos.web.pojo.BossJobDetail;
import com.coderdream.demos.web.service.BossJobDetailService;
import com.coderdream.demos.web.mapper.BossJobDetailMapper;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_boss_job_detail】的数据库操作Service实现
 * @createDate 2024-07-31 23:50:42
 */
@Service
public class BossJobDetailServiceImpl extends ServiceImpl<BossJobDetailMapper, BossJobDetail>
    implements BossJobDetailService {

    @Resource
    private BossJobDetailMapper bossJobDetailMapper;

    @Override
    public int insertOrUpdateBatch(@Param("entities") List<BossJobDetail> entities) {
        return bossJobDetailMapper.insertOrUpdateBatch(entities);
    }

//    @Override
//    public boolean save(BossJobDetail entity) {
//        BossJobDetailMapper bossJobDetailMapper = new BossJobDetailMapper();
//        return bossJobDetailMapper.insert(entity) == 1;
//    }
}




