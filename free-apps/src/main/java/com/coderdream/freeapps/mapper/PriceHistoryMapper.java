package com.coderdream.freeapps.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.PriceHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_price_history】的数据库操作Mapper
* @createDate 2023-03-03 08:38:02
* @Entity com.coderdream.freeapps.model.PriceHistory
*/
public interface PriceHistoryMapper extends BaseMapper<PriceHistory> {
    int insertSelective(PriceHistory priceHistory);

    int insertBatch(@Param("priceHistoryCollection") Collection<PriceHistory> priceHistoryCollection);

    int insertOrUpdateBatch(List<PriceHistory> entities);
}




