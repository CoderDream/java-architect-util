package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.Product;
import com.coderdream.mybatisplusdemo.mapper.ProductMapper;
import com.coderdream.mybatisplusdemo.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl实现了IService，提供了IService中基础功能的实现
 * 若ServiceImpl无法满足业务需求，则可以使用自定的ProductService定义方法，并在实现类中实现
 */
@Service //指定业务层组建
@DS("slave_1") //指定所操作的数据源
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
