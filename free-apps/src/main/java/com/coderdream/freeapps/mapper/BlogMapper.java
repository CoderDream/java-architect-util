package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.BlogEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_blog】的数据库操作Mapper
* @createDate 2023-05-19 19:04:42
* @Entity com.coderdream.freeapps.model.BlogEntity
*/
public interface BlogMapper extends BaseMapper<BlogEntity> {
    int insertOrUpdateBatch(List<BlogEntity> entities);
}




