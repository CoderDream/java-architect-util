package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.AppIcon;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_appIcon】的数据库操作Service
* @createDate 2023-03-11 10:37:56
*/
public interface AppIconService extends IService<AppIcon> {

    int insertOrUpdateBatch(List<AppIcon> appIconList);

    List<AppIcon> selectList(AppIcon appIcon);

    List<AppIcon> selectDoneList(AppIcon appIcon);
    List<AppIcon> selectTodoList(AppIcon appIcon);

    public void dailyProcess();

}
