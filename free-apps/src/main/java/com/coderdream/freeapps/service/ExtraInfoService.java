package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.ExtraInfo;

import java.util.List;

public interface ExtraInfoService extends IService<ExtraInfo> {
    List<ExtraInfo> selectAll();
}
