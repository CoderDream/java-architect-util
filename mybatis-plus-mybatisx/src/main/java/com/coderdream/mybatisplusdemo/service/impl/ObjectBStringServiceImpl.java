package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.ObjectBString;
import com.coderdream.mybatisplusdemo.service.ObjectBStringService;
import com.coderdream.mybatisplusdemo.mapper.ObjectBStringMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【object_b_string(短文本)】的数据库操作Service实现
* @createDate 2023-02-27 15:54:35
*/
@Service
public class ObjectBStringServiceImpl extends ServiceImpl<ObjectBStringMapper, ObjectBString>
    implements ObjectBStringService{

}




