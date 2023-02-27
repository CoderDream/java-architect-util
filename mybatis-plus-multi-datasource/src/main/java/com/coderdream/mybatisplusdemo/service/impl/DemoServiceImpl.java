package com.coderdream.mybatisplusdemo.service.impl;

import com.coderdream.mybatisplusdemo.entity.Demo;
import com.coderdream.mybatisplusdemo.mapper.DemoMapper;
import com.coderdream.mybatisplusdemo.service.IDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CoderDream
 * @since 2022-09-12
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

}
