package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.CrawlerHistory;

import java.util.List;

/**
 * @author CoderDream
 * @description 针对表【t_crawler_history】的数据库操作Mapper
 * @createDate 2023-02-28 10:11:06
 * @Entity com.coderdream.freeapps.model.CrawlerHistory
 */
public interface CrawlerHistoryMapper extends BaseMapper<CrawlerHistory> {
    int insertOrUpdateBatch(List<CrawlerHistory> entities);
}




