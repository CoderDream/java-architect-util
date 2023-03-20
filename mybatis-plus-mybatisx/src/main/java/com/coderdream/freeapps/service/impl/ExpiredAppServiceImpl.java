package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.model.ExpiredApp;
import com.coderdream.freeapps.service.ExpiredAppService;
import com.coderdream.freeapps.mapper.ExpiredAppMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_expired_app】的数据库操作Service实现
* @createDate 2023-03-13 21:22:58
*/
@Service
public class ExpiredAppServiceImpl extends ServiceImpl<ExpiredAppMapper, ExpiredApp>
    implements ExpiredAppService{

}




