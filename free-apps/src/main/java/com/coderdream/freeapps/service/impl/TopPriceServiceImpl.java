package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.TopPriceMapper;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.model.TopPrice;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.TopPriceService;
import javax.transaction.Transactional;
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
    @Transactional
    public void process(String dateStr) {
        List<TopPrice> oldTopPriceList = selectList(null);
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        Map<String, TopPrice> mapTopPrice = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(oldTopPriceList)) {
            for (TopPrice topPrice : oldTopPriceList) {
                map.put(topPrice.getAppId(), topPrice.getTopPrice());
                mapTopPrice.put(topPrice.getAppId(), topPrice);
            }
        }
        PriceHistory priceHistoryQuery = new PriceHistory();

        if (StrUtil.isNotEmpty(dateStr)) {
            try {
                Date crawlerDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                priceHistoryQuery.setCrawlerDate(crawlerDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        List<TopPrice> topPriceList = null;
        TopPrice topPrice;
        BigDecimal tempPrice;
        BigDecimal newPrice;
        List<PriceHistory> priceHistoryList = priceHistoryService.selectList(priceHistoryQuery);
        if (!CollectionUtils.isEmpty(priceHistoryList)) {
            topPriceList = new ArrayList<>();
            for (PriceHistory priceHistory : priceHistoryList) {
                // 解析价格
                newPrice = parsePrice(priceHistory);
                if(priceHistory.getAppId().equals("id1563436064")) {
                    log.error("#############");
                }
                tempPrice = map.get(priceHistory.getAppId());
                if (tempPrice != null) {
                    topPrice = mapTopPrice.get(priceHistory.getAppId());// new TopPrice();
                    // 如果价格大于0，则刷新价格
                    if (newPrice.compareTo(new BigDecimal(0)) > 0) {
                        topPrice.setLatestPrice(newPrice);
                        topPrice.setLatestPriceDate(priceHistoryQuery.getCrawlerDate());
                        topPrice.setLastModifiedDate(new Date());
                        topPriceList.add(topPrice);
                    }

                    // 如果价格大于最高价，则刷新最高价
                    if (newPrice.compareTo(tempPrice) > 0) {
                        topPrice.setTopPrice(newPrice);
                        topPrice.setTopPriceDate(priceHistoryQuery.getCrawlerDate());
                        topPrice.setLastModifiedDate(new Date());
                        topPriceList.add(topPrice);
                    }
                } else {
                    topPrice = new TopPrice();
                    topPrice.setLatestPrice(newPrice);
                    topPrice.setLatestPriceDate(priceHistoryQuery.getCrawlerDate());
                    topPrice.setTopPrice(newPrice);
                    topPrice.setTopPriceDate(priceHistoryQuery.getCrawlerDate());
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

        // 四舍五入取整
        if (priceHistory.getPriceUs() != null) {
            topPrice = priceHistory.getPriceUs();
            topPrice = topPrice.multiply(new BigDecimal(6));
            topPrice = topPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
        }

        return topPrice;
    }

}




