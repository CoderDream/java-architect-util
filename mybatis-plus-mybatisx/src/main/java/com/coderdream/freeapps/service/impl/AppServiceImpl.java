package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.mapper.AppMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_app】的数据库操作Service实现
* @createDate 2023-03-03 08:38:01
*/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
    implements AppService{

}




