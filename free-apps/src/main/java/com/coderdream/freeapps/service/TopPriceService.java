package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.TopPrice;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_top_price】的数据库操作Service
* @createDate 2023-03-13 21:22:58
*/
public interface TopPriceService extends IService<TopPrice> {

    int insertOrUpdateBatch(List<TopPrice> topPriceList);


    List<TopPrice> selectList(TopPrice topPrice);

    void process(String dateStr);
}
