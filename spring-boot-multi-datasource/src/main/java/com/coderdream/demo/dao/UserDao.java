package com.coderdream.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/8 14:05
 * ClassName:UserDao
 * 使用mybatis-plus,每个mapper接口都需要继承BaseMapper
 * BaseMapper类描述： Mapper继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 */
@Repository // 表示持久层
public interface UserDao extends BaseMapper<User> {

}
