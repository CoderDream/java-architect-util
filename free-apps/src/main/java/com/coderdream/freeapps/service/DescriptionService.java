package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.model.Description;
import java.util.List;

/**
 * @author CoderDream
 * @description 针对表【t_description】的数据库操作Service
 * @createDate 2023-03-11 10:37:56
 */
public interface DescriptionService extends IService<Description> {

    int insertOrUpdateBatch(List<Description> descriptionList);


    int insertOrUpdateBatchMy(List<Description> descriptionList);

    int dailyProcess();
}
