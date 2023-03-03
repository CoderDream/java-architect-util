package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.mapper.PriceHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_price_history】的数据库操作Service实现
* @createDate 2023-03-03 08:38:02
*/
@Service
public class PriceHistoryServiceImpl extends ServiceImpl<PriceHistoryMapper, PriceHistory>
    implements PriceHistoryService{

}




