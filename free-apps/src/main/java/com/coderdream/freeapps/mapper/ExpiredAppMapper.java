package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.ExpiredApp;
import com.coderdream.freeapps.model.TopPrice;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_expired_app】的数据库操作Mapper
* @createDate 2023-03-13 21:22:58
* @Entity com.coderdream.freeapps.model.ExpiredApp
*/
public interface ExpiredAppMapper extends BaseMapper<ExpiredApp> {

    int insertOrUpdateBatch(List<ExpiredApp> entities);
}




