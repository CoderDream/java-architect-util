package com.coderdream.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.mybatisplusdemo.entity.Product;
import org.springframework.stereotype.Repository;

@Repository  //需要持久层组件 保证test测试类中不报错
public interface ProductMapper extends BaseMapper<Product> {
}
