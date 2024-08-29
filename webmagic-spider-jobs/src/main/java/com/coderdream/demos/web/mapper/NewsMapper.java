package com.coderdream.demos.web.mapper;

import com.coderdream.demos.web.pojo.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author CoderDream
* @description 针对表【t_news】的数据库操作Mapper
* @createDate 2024-07-31 17:26:04
* @Entity com.coderdream.demos.web.pojo.News
*/
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}




