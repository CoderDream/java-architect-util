package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.CategoryEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_category】的数据库操作Service
* @createDate 2023-05-19 19:04:41
*/
public interface CategoryService extends IService<CategoryEntity> {
//    ServiceImpl<AppMapper, AppEntity> implements AppService
    List<CategoryEntity> list(CategoryEntity categoryEntity);

    boolean initCategory();

    int insertOrUpdateBatch(List<CategoryEntity> categoryEntityList);
}
