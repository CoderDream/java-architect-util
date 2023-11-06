package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.CategoryMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.CategoryEntity;
import com.coderdream.freeapps.service.CategoryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_category】的数据库操作Service实现
 * @createDate 2023-05-19 19:04:41
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private AppMapper appMapper;

    @Override
    public List<CategoryEntity> list(CategoryEntity categoryEntity) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public boolean initCategory() {
        // 利用stream进行类型转化

        List<String> stringList = new ArrayList<>();
        stringList.add("a11");
        stringList.add("b11");
        stringList.add("c11");
        stringList.add("d11");
        stringList.add("e11");
        List<Map<String, String>> stringList1 = stringList.stream().map(item ->
            {
                Map<String, String> map = new HashMap<>();
                map.put("name", item.toUpperCase());
                return map;
            }
        ).collect(Collectors.toList());
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct category, count(*) as cnt");
        queryWrapper.groupBy("category");
        queryWrapper.orderByDesc("cnt ");
        queryWrapper.eq("delete_flag", 0);
        List<AppEntity> appEntityList = appMapper.selectList(queryWrapper);

        List<CategoryEntity>  categoryEntityList = appEntityList.stream().map(appEntity -> {
            if(appEntity != null) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setName(appEntity.getCategory());
                return categoryEntity;
            }
            return null;
        }).collect(Collectors.toList());

//        List<CategoryEntity> categoryEntityList = new ArrayList<>();
//        for (AppEntity appEntity : appEntityList) {
//            CategoryEntity categoryEntity = new CategoryEntity();
//            if (StrUtil.isNotEmpty(appEntity.getCategory())) {
//                categoryEntity.setName(appEntity.getCategory());
//                categoryEntityList.add(categoryEntity);
//            }
//        }

        return saveBatch(categoryEntityList);
    }

    @Override
    public int insertOrUpdateBatch(List<CategoryEntity> categoryEntityList) {
        return 0;
    }
}




