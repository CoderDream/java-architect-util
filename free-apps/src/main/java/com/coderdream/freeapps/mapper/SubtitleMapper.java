package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.BlogEntity;
import com.coderdream.freeapps.model.SubtitleEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_subtitle】的数据库操作Mapper
* @createDate 2023-06-16 19:35:48
* @Entity com.coderdream.freeapps.model.SubtitleEntity
*/
public interface SubtitleMapper extends BaseMapper<SubtitleEntity> {

    int insertOrUpdateBatch(List<SubtitleEntity> entities);
}




