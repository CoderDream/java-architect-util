package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.controller.SyncTaskReqDto;
import com.coderdream.freeapps.model.SyncTaskEntity;

/**
* @author CoderDream
* @description 针对表【t_sync_task】的数据库操作Service
* @createDate 2023-04-05 21:23:43
*/
public interface SyncTaskService extends IService<SyncTaskEntity> {

    void dailyProcess(SyncTaskReqDto syncTaskReqDto);

    void dailyRecommend();
}
