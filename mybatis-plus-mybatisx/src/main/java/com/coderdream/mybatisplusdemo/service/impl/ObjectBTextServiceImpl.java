package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.ObjectBText;
import com.coderdream.mybatisplusdemo.service.ObjectBTextService;
import com.coderdream.mybatisplusdemo.mapper.ObjectBTextMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【object_b_text(长文本)】的数据库操作Service实现
* @createDate 2023-02-27 15:54:35
*/
@Service
public class ObjectBTextServiceImpl extends ServiceImpl<ObjectBTextMapper, ObjectBText>
    implements ObjectBTextService{

}




