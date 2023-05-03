package com.coderdream.freeapps.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.FreeHistory;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_free_history】的数据库操作Mapper
* @createDate 2023-02-27 19:08:25
* @Entity com.coderdream.freeapps.entity.FreeHistory
*/
public interface FreeHistoryMapper extends BaseMapper<FreeHistory> {

//    int insertSelective(FreeHistory freeHistory);

    int insertSelective(FreeHistory freeHistory);

    int insertOrUpdateBatch(List<FreeHistory> entities);
    int updateRecommendFlagBatch(List<FreeHistory> entities);

}




