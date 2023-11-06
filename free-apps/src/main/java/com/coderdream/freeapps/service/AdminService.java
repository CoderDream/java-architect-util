package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.AdminEntity;

/**
 * @author CoderDream
 * @description 针对表【t_admin】的数据库操作Service
 * @createDate 2023-05-19 19:04:42
 */
public interface AdminService extends IService<AdminEntity> {

    AdminEntity login(AdminEntity adminEntity);
}
