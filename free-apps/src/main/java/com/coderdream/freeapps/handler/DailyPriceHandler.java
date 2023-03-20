package com.coderdream.freeapps.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.*;
import com.coderdream.freeapps.util.Constants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.ListUtils;
import com.coderdream.freeapps.util.TxtUtils;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/27 4:28 下午
 */
@Component
@RequiredArgsConstructor
public class DailyPriceHandler extends IJobHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DailyPriceHandler.class);

    @Resource
    private AppService appService;

    @Resource
    private PriceHistoryService priceHistoryService; //这里可能爆红，但是运行没问题

    @XxlJob(value = "dailyPriceHandler")
    @Override
    public void execute() throws Exception {
        priceHistoryService.dailyProcess();
    }
}
