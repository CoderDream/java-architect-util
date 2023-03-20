package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.ExpiredApp;
import com.coderdream.freeapps.model.TopPrice;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_expired_app】的数据库操作Service
* @createDate 2023-03-13 21:22:58
*/
public interface ExpiredAppService extends IService<ExpiredApp> {

    int insertOrUpdateBatch(List<ExpiredApp> expiredAppList);
}
