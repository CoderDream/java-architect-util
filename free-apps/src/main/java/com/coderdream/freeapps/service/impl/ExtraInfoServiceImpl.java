package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.ExtraInfoMapper;
import com.coderdream.freeapps.model.ExtraInfo;
import com.coderdream.freeapps.service.ExtraInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("extraInfoService")
public class ExtraInfoServiceImpl extends ServiceImpl<ExtraInfoMapper, ExtraInfo> implements ExtraInfoService {
    @Resource
    ExtraInfoMapper extraInfoMapper;

    @Override
    public List<ExtraInfo> selectAll() {
        return extraInfoMapper.selectList(null);
    }
}
