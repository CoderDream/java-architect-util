package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.SubtitleEntity;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_subtitle】的数据库操作Service
* @createDate 2023-06-16 19:35:48
*/
public interface SubtitleService extends IService<SubtitleEntity> {

    int initRecord(List<SubtitleEntity> entities);
}
