package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.CategoryEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_category】的数据库操作Mapper
* @createDate 2023-05-19 19:04:41
* @Entity com.coderdream.freeapps.model.CategoryEntity
*/
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
    int insertOrUpdateBatch(List<CategoryEntity> entities);
}
