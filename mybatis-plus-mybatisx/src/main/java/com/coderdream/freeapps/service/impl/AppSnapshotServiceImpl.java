package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.model.AppSnapshot;
import com.coderdream.freeapps.service.AppSnapshotService;
import com.coderdream.freeapps.mapper.AppSnapshotMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_app_snapshot(APP截图信息表)】的数据库操作Service实现
* @createDate 2023-02-28 10:11:06
*/
@Service
public class AppSnapshotServiceImpl extends ServiceImpl<AppSnapshotMapper, AppSnapshot>
    implements AppSnapshotService{

}




