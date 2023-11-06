package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.BlogMapper;
import com.coderdream.freeapps.mapper.CategoryMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.BlogEntity;
import com.coderdream.freeapps.model.CategoryEntity;
import com.coderdream.freeapps.service.BlogService;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_blog】的数据库操作Service实现
 * @createDate 2023-05-19 19:04:42
 */
@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity>
    implements BlogService {

    @Resource
    private AppMapper appMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private BlogMapper blogMapper;

    @Override
    public int insertOrUpdateBatch(List<BlogEntity> blogEntityList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(blogEntityList)) {
            log.info("本次批量执行的记录条数: " + blogEntityList.size());
            // 分批处理
            List<List<BlogEntity>> lists = CdListUtils.splitTo(blogEntityList, CdConstants.BATCH_UPDATE_ROWS);
            for (List<BlogEntity> list : lists) {
                count += blogMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }

    @Override
    public List<BlogEntity> list(BlogEntity blogEntity) {
        QueryWrapper<BlogEntity> queryWrapper = new QueryWrapper<>();
        return blogMapper.selectList(queryWrapper);
    }

    @Override
    public Integer dailyProcess() {
        long startTime = System.currentTimeMillis();
        int recordAmount = 0;
        QueryWrapper<CategoryEntity> categoryEntityQueryWrapper = new QueryWrapper<>();
        List<CategoryEntity> categoryEntityList = categoryMapper.selectList(categoryEntityQueryWrapper);
        Map<String, Long> categoryIdMap = categoryEntityList.stream()
            .collect(Collectors.toMap(CategoryEntity::getName, CategoryEntity::getId));

        QueryWrapper<AppEntity> appEntityQueryWrapper = new QueryWrapper<>();
        appEntityQueryWrapper.eq("delete_flag", 0);
        List<AppEntity> appEntityList = appMapper.selectList(appEntityQueryWrapper);

        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<>();
        List<BlogEntity> blogEntityList = blogMapper.selectList(blogEntityQueryWrapper);

        Set<String> appIdSet = null;
        if (CollectionUtils.isNotEmpty(blogEntityList)) {
            appIdSet = blogEntityList.stream().flatMap(appEntity -> {
                if (StrUtil.isEmpty(appEntity.getAppId())) {
                    return null;
                }
                return Arrays.stream(appEntity.getAppId().split(","));
            }).collect(Collectors.toSet());
        }

        if (CollectionUtils.isNotEmpty(appEntityList)) {
            List<BlogEntity> blogEntityListNew = new ArrayList<>();
            List<BlogEntity> blogEntityListUpdate = new ArrayList<>();
            BlogEntity blogEntity;
            String appId = "";
            for (AppEntity appEntity : appEntityList) {
                blogEntity = new BlogEntity();
                appId = appEntity.getAppId();
                if(appIdSet != null && appIdSet.contains(appId)) {
                    blogEntity.setTitle(appEntity.getTitle());
                    blogEntity.setCategoryId(categoryIdMap.get(appEntity.getCategory()));
                    if (StrUtil.isNotEmpty(appEntity.getDescriptionCn())) {
                        blogEntity.setContent(appEntity.getDescriptionCn());
                    } else {
                        blogEntity.setContent(appEntity.getDescriptionUs());
                    }
                    blogEntity.setAppId(appId);
//                    blogEntityListUpdate.add(blogEntity);
                } else {
                    blogEntity.setTitle(appEntity.getTitle());
                    blogEntity.setCategoryId(categoryIdMap.get(appEntity.getCategory()));
                    if (StrUtil.isNotEmpty(appEntity.getDescriptionCn())) {
                        blogEntity.setContent(appEntity.getDescriptionCn());
                    } else {
                        blogEntity.setContent(appEntity.getDescriptionUs());
                    }
                    blogEntity.setCreateTime(new Date());
                    blogEntity.setAppId(appId);
                    blogEntityListNew.add(blogEntity);
                }
            }
            if (CollectionUtils.isNotEmpty(blogEntityListNew)) {
                this.insertOrUpdateBatch(blogEntityListNew);
            }
            if (CollectionUtils.isNotEmpty(blogEntityListUpdate)) {
                this.insertOrUpdateBatch(blogEntityListUpdate);
            }
        }
        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        log.error("本次记录条数" + recordAmount + ", 耗时" + CdDateTimeUtils.genMessage(period) + "。");
        return recordAmount;
    }
}




