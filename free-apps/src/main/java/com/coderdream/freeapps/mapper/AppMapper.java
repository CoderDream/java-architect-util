package com.coderdream.freeapps.mapper;

import com.coderdream.freeapps.dto.DailyPptInfo;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.App;

import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * @author CoderDream
 * @description 针对表【t_app】的数据库操作Mapper
 * @createDate 2023-02-27 19:08:25
 * @Entity com.coderdream.freeapps.entity.App
 */
public interface AppMapper extends BaseMapper<App> {

//    int insertSelective(App app);

//    int insertSelective(App app);

//    int insertBatch(@Param("appCollection") Collection<App> appCollection);

    int insertOrUpdateBatch(List<App> entities);

    @Select(
        " SELECT " +
        " 	t1.app_id as appId, " +
        " 	t1.title, " +
        " 	t0.rate_amount as rateAmount, " +
        " 	t1.price as yesterdayPrice, " +
        " 	t2.price as todayPrice, " +
        " 	td.*  " +
        " FROM " +
        " 	t_price_history t1 " +
        " 	INNER JOIN t_price_history t2 " +
        " 	LEFT JOIN t_description td ON t1.app_id = td.app_id " +
        " 	LEFT JOIN t_app t0 ON t0.app_id = t1.app_id  " +
        " WHERE " +
        " 	TO_DAYS(NOW()) - TO_DAYS( t1.crawler_date ) = 1  " +
        " 	AND to_days(t2.crawler_date) = to_days(NOW())  " +
        " 	AND t1.price_str <> t2.price_str  " +
        " 	AND t1.app_id = t2.app_id  " +
        " 	AND ( t1.price_str <> 'Free' AND t1.price_str <> '免费' )  " +
        " 	AND ( t2.price_str = 'Free' OR t2.price_str = '免费' ) "
    )
    List<DailyPptInfo> selectDailyPptInfo();
}




