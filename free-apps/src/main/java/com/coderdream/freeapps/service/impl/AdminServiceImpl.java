package com.coderdream.freeapps.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AdminMapper;
import com.coderdream.freeapps.model.AdminEntity;
import com.coderdream.freeapps.service.AdminService;
import com.github.yitter.idgen.YitIdHelper;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_admin】的数据库操作Service实现
* @createDate 2023-05-19 19:04:41
*/
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity>
    implements AdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override
    public AdminEntity login(AdminEntity adminEntity) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", adminEntity.getAccount());
        queryWrapper.eq("password", adminEntity.getPassword());
        AdminEntity admin = adminMapper.selectOne(queryWrapper);
        if(admin != null) {
            long newId = YitIdHelper.nextId();
            String token = newId + "";
            token = UUID.fastUUID().toString();
            log.error("token: " + token);
            UpdateWrapper<AdminEntity> updateWrapper= new UpdateWrapper();
            updateWrapper.eq("id", admin.getId());
            admin.setToken(token);
            adminMapper.update(admin, updateWrapper);
            admin.setPassword("");
            return admin;
        }

        log.error("用户名密码无效: " + adminEntity);
        return null;
    }
}




