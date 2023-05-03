package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.Description;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_description】的数据库操作Mapper
* @createDate 2023-03-11 10:37:56
* @Entity com.coderdream.freeapps.model.Description
*/
public interface DescriptionMapper extends BaseMapper<Description> {

    int insertOrUpdateBatch(List<Description> entities);
    int insertOrUpdateBatchBaseDescription(List<Description> entities);
}




