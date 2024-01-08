package com.coderdream.freeapps;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.dto.TopList;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.CrawlerHistory;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.util.multithread.Demo02;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import com.coderdream.freeapps.util.other.CdListUtils;
import com.coderdream.freeapps.util.other.JSoupUtil;
import com.coderdream.freeapps.vo.AppVO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AppMapperTest {

    @Autowired
    private AppMapper appMapper;

    @Test
    void testQueryWrapper() {
        // 1.构建查询条件 where name like "%o%" AND balance >= 1000
        QueryWrapper<AppEntity> wrapper = new QueryWrapper<AppEntity>()
            .select("id", "title")
            .like("title", "搜");
//            .ge("balance", 1000);
        // 2.查询数据
        List<AppEntity> users = appMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
