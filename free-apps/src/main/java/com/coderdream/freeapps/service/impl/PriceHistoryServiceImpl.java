package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.PriceHistoryMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.PriceHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_price_history】的数据库操作Service实现
* @createDate 2023-03-03 08:38:02
*/
@Service
public class PriceHistoryServiceImpl extends ServiceImpl<PriceHistoryMapper, PriceHistory>
    implements PriceHistoryService{

    @Resource
    private PriceHistoryMapper priceHistoryMapper;
    @Override
    public int insertSelective(PriceHistory priceHistory) {
            return priceHistoryMapper.insertSelective(priceHistory);
    }

    @Override
    public int insertOrUpdateBatch(List<PriceHistory> priceHistoryList) {
        return priceHistoryMapper.insertOrUpdateBatch(priceHistoryList);
    }

    @Override
    public List<PriceHistory> selectList(PriceHistory priceHistory) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(priceHistory.getAppId())) {
            queryWrapper.eq("app_id", priceHistory.getAppId());
        }
        if (priceHistory.getCrawlerDate()!=null) {
            queryWrapper.eq("crawler_date", priceHistory.getCrawlerDate());
        }
        List<PriceHistory> result = priceHistoryMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<PriceHistory> selectDoneList(PriceHistory priceHistory) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        try {
            queryWrapper.eq("crawler_date", dateFormat.parse(dateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        queryWrapper.isNotNull("price_str");
//        queryWrapper.isNull("del_flag");
//        queryWrapper.ne("del_flag", 1);
        List<PriceHistory> priceHistoryList = priceHistoryMapper.selectList(queryWrapper);
        return priceHistoryList;
    }



    @Override
    public IPage<PriceHistory> selectPage(Page<PriceHistory> page) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        return priceHistoryMapper.selectPage(page, queryWrapper);
    }

}




