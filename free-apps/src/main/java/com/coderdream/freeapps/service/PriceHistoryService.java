package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.model.PriceHistory;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_price_history】的数据库操作Service
* @createDate 2023-03-03 08:38:02
*/
public interface PriceHistoryService extends IService<PriceHistory> {
    int insertSelective(PriceHistory priceHistory);

    int insertOrUpdateBatch(List<PriceHistory> priceHistoryList);

    List<PriceHistory> selectList(PriceHistory priceHistory);

    List<PriceHistory> selectDoneList(PriceHistory priceHistory);

    IPage<PriceHistory> selectPage(Page<PriceHistory> page);

    void dailyProcess();
    void dailyProcessV2();

    void dailyProcessSimple();

    void processPriceNone();
    void processPriceFault();

    void updateTodayFreeAppPrice();

    void processPriceTopList();
}
