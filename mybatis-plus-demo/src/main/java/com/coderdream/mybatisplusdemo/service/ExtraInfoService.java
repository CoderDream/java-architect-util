package com.coderdream.mybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.mybatisplusdemo.entity.ExtraInfo;

import java.util.List;

public interface ExtraInfoService extends IService<ExtraInfo> {
    List<ExtraInfo> selectAll();
}
