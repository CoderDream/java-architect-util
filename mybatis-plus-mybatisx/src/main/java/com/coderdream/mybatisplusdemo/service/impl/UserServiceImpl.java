package com.coderdream.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.mybatisplusdemo.entity.User;
import com.coderdream.mybatisplusdemo.service.UserService;
import com.coderdream.mybatisplusdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-27 15:56:18
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




