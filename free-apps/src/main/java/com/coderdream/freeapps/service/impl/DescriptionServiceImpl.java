package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.DescriptionMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author CoderDream
 * @description 针对表【t_description】的数据库操作Service实现
 * @createDate 2023-03-11 10:37:56
 */
@Service
@Slf4j
public class DescriptionServiceImpl extends ServiceImpl<DescriptionMapper, Description> implements DescriptionService {

    @Resource
    private DescriptionMapper descriptionMapper;
    @Resource
    private AppMapper appMapper;

    @Override
    public int insertOrUpdateBatch(List<Description> descriptionList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(descriptionList)) {
            log.info("本次批量执行的记录条数: " + descriptionList.size());
            // 分批处理
            List<List<Description>> lists = CdListUtils.splitTo(descriptionList, Constants.BATCH_INSERT_UPDATE_ROWS);
            for (List<Description> list : lists) {
                count += descriptionMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }

    @Override
    public int insertOrUpdateBatchMy(List<Description> descriptionList) {
        //    QueryWrapper<App> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("del_flag", 0);
//    List<App> appList = appMapper.selectList(queryWrapper);
//    List<Description> descriptionList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(appList)) {
//        for (App app : appList) {
//            UpdateWrapper updateWrapper = new UpdateWrapper();
//            updateWrapper.eq("app_id", app.getAppId());
//            updateWrapper.set("status",  1);
//            updateWrapper.set("nickname",  "张三");
//            descriptionMapper.update(null, updateWrapper);
//        }
//    }
        return 0;
    }

    @Override
    public int dailyProcess() {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        List<App> appList = appMapper.selectList(queryWrapper);
        List<Description> descriptionList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(appList)) {
            Description description;
            for (App app : appList) {
                description = new Description();
                BeanUtils.copyProperties(app, description);
                descriptionList.add(description);
            }
        }

        int count = 0;
        if (!CollectionUtils.isEmpty(descriptionList)) {
            log.info("本次批量执行的记录条数: " + descriptionList.size());
            // 分批处理
            List<List<Description>> lists = CdListUtils.splitTo(descriptionList, Constants.BATCH_INSERT_UPDATE_ROWS);
            for (List<Description> list : lists) {
                count += descriptionMapper.insertOrUpdateBatchBaseDescription(list);
            }
        }

        return count;
    }


}




