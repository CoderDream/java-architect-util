package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.CrawlerHistoryMapper;
import com.coderdream.freeapps.model.CrawlerHistory;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CoderDream
 * @description 针对表【t_crawler_history】的数据库操作Service实现
 * @createDate 2023-02-28 10:11:06
 */
@Service
public class CrawlerHistoryServiceImpl extends ServiceImpl<CrawlerHistoryMapper, CrawlerHistory>
        implements CrawlerHistoryService{

    @Resource
    private CrawlerHistoryMapper crawlerHistoryMapper;
    @Override
    public int insertOrUpdateBatch(List<CrawlerHistory> crawlerHistoryList) {
        return crawlerHistoryMapper.insertOrUpdateBatch(crawlerHistoryList);
    }
}




