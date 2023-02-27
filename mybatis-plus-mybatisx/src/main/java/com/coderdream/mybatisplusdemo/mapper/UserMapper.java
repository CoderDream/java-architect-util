package com.coderdream.mybatisplusdemo.mapper;

import com.coderdream.mybatisplusdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author CoderDream
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-02-27 15:56:18
* @Entity com.coderdream.mybatisplusdemo.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    // https://baomidou.com/pages/ba5b24/#%E5%8A%9F%E8%83%BD
    int insertSelective(User user);
    int delByIdAndName(@Param("id") Long id, @Param("name") String name);
}





