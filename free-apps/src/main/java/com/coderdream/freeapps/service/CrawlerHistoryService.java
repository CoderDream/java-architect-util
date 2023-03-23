package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.CrawlerHistory;

import java.util.List;

/**
 * @author CoderDream
 * @description 针对表【t_crawler_history】的数据库操作Service
 * @createDate 2023-02-28 10:11:06
 */
public interface CrawlerHistoryService extends IService<CrawlerHistory> {

    int insertOrUpdateBatch(List<CrawlerHistory> appList);
}