package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.AppIcon;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_snapshot】的数据库操作Mapper
* @createDate 2023-03-11 10:37:56
* @Entity com.coderdream.freeapps.model.AppIcon
*/
public interface AppIconMapper extends BaseMapper<AppIcon> {

    int insertOrUpdateBatch(List<AppIcon> entities);
}




