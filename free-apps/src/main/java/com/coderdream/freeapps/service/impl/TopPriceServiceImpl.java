package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.ExpiredAppMapper;
import com.coderdream.freeapps.mapper.TopPriceMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.model.TopPrice;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.TopPriceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CoderDream
 * @description 针对表【t_top_price】的数据库操作Service实现
 * @createDate 2023-03-13 21:22:58
 */
@Service
public class TopPriceServiceImpl extends ServiceImpl<TopPriceMapper, TopPrice>
        implements TopPriceService {

    @Resource
    private TopPriceMapper topPriceMapper;

    @Resource
    private PriceHistoryService priceHistoryService;

    @Override
    public int insertOrUpdateBatch(List<TopPrice> topPriceList) {
        return topPriceMapper.insertOrUpdateBatch(topPriceList);
    }

    @Override
    public List<TopPrice> selectList(TopPrice topPrice) {
        QueryWrapper<TopPrice> queryWrapper = new QueryWrapper<>();
//        if (StrUtil.isNotEmpty(app.getAppId())) {
//            queryWrapper.eq("app_id", app.getAppId());
//        }
        List<TopPrice> result = topPriceMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public void process(String dateStr) {
        List<TopPrice> oldTopPriceList = selectList(null);
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(oldTopPriceList)) {
            for (TopPrice topPrice : oldTopPriceList) {
                map.put(topPrice.getAppId(), topPrice.getTopPrice());
            }
        }
        PriceHistory priceHistoryQuery = new PriceHistory();
        Date crawlerDate;
        try {
            crawlerDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<TopPrice> topPriceList = null;
        TopPrice topPrice;
        priceHistoryQuery.setCrawlerDate(crawlerDate);
        BigDecimal tempPrice;
        BigDecimal newPrice;
        List<PriceHistory> priceHistoryList = priceHistoryService.selectList(priceHistoryQuery);
        if (!CollectionUtils.isEmpty(priceHistoryList)) {
            topPriceList = new ArrayList<>();
            for (PriceHistory priceHistory : priceHistoryList) {
                newPrice = parsePrice(priceHistory);
                tempPrice = map.get(priceHistory.getAppId());
                if (tempPrice != null) {
                    if (newPrice.compareTo(tempPrice) == 1) {
                        topPrice = new TopPrice();
                        topPrice.setTopPrice(tempPrice);
                        topPrice.setAppId(priceHistory.getAppId());
                        topPrice.setLastModifiedDate(new Date());
                        topPriceList.add(topPrice);
                    }
                } else {
                    topPrice = new TopPrice();
                    topPrice.setTopPrice(newPrice);
                    topPrice.setAppId(priceHistory.getAppId());
                    topPrice.setCreatedDate(new Date());
                    topPriceList.add(topPrice);
                }

            }
        }
        if (!CollectionUtils.isEmpty(topPriceList)) {
            insertOrUpdateBatch(topPriceList);
        }
    }

    private BigDecimal parsePrice(PriceHistory priceHistory) {
        BigDecimal topPrice = new BigDecimal(0);
        if (priceHistory.getPriceCn() != null) {
            topPrice = BigDecimal.valueOf(priceHistory.getPriceCn());
        }

        if (priceHistory.getPriceUs() != null) {
            topPrice = priceHistory.getPriceUs();
        }

        return topPrice;
    }

}




