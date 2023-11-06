package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.ActResultLogEntity;
import javax.servlet.http.HttpServletResponse;

/**
* @author CoderDream
* @description 针对表【t_act_result_log】的数据库操作Service
* @createDate 2023-06-07 10:52:09
*/
public interface ActResultLogService extends IService<ActResultLogEntity> {
    void dataExport300w(HttpServletResponse response);
}
