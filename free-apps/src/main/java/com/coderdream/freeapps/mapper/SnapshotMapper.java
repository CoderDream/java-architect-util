package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.Snapshot;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_snapshot】的数据库操作Mapper
* @createDate 2023-03-11 10:37:56
* @Entity com.coderdream.freeapps.model.Snapshot
*/
public interface SnapshotMapper extends BaseMapper<Snapshot> {

    int insertOrUpdateBatch(List<Snapshot> entities);
}




