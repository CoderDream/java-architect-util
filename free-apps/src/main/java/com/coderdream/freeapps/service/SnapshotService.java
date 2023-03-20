package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.model.Snapshot;

import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_snapshot】的数据库操作Service
* @createDate 2023-03-11 10:37:56
*/
public interface SnapshotService extends IService<Snapshot> {

    int insertOrUpdateBatch(List<Snapshot> snapshotList);

    List<Snapshot> selectList(Snapshot snapshot);

    List<Snapshot> selectDoneList(Snapshot snapshot);
    List<Snapshot> selectTodoList(Snapshot snapshot);

    public void dailyProcess();

}
