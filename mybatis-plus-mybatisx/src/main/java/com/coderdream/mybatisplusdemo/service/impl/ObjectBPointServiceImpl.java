package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.ObjectBPoint;
import com.coderdream.mybatisplusdemo.service.ObjectBPointService;
import com.coderdream.mybatisplusdemo.mapper.ObjectBPointMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【object_b_point(地理信息-点数据)】的数据库操作Service实现
* @createDate 2023-02-27 15:54:35
*/
@Service
public class ObjectBPointServiceImpl extends ServiceImpl<ObjectBPointMapper, ObjectBPoint>
    implements ObjectBPointService{

}




