package com.coderdream.freeapps.mapper;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_app】的数据库操作Mapper
* @createDate 2023-02-27 19:08:25
* @Entity com.coderdream.freeapps.entity.App
*/
public interface AppMapper extends BaseMapper<App> {

//    int insertSelective(App app);

    int insertSelective(App app);

    int insertBatch(@Param("appCollection") Collection<App> appCollection);

    int insertOrUpdateBatch(List<App> entities);
}




