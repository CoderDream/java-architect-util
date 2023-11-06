package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.BlogEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_blog】的数据库操作Service
* @createDate 2023-05-19 19:04:42
*/
public interface BlogService extends IService<BlogEntity> {

    List<BlogEntity> list(BlogEntity blogEntity);
    int insertOrUpdateBatch(List<BlogEntity> blogEntityList);
    public Integer dailyProcess();
}
