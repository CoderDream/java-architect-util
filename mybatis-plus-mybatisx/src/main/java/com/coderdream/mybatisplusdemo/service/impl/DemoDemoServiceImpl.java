package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.DemoDemo;
import com.coderdream.mybatisplusdemo.service.DemoDemoService;
import com.coderdream.mybatisplusdemo.mapper.DemoDemoMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_demo_demo】的数据库操作Service实现
* @createDate 2023-02-27 15:38:31
*/
@Service
public class DemoDemoServiceImpl extends ServiceImpl<DemoDemoMapper, DemoDemo>
    implements DemoDemoService{

}




