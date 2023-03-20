package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.ExpiredAppMapper;
import com.coderdream.freeapps.model.ExpiredApp;
import com.coderdream.freeapps.service.ExpiredAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_expired_app】的数据库操作Service实现
* @createDate 2023-03-13 21:22:58
*/
@Service
public class ExpiredAppServiceImpl extends ServiceImpl<ExpiredAppMapper, ExpiredApp>
    implements ExpiredAppService{

    @Resource
    private ExpiredAppMapper expiredAppMapper;

    @Override
    public int insertOrUpdateBatch(List<ExpiredApp> expiredAppList) {
        return expiredAppMapper.insertOrUpdateBatch(expiredAppList);
    }
}




