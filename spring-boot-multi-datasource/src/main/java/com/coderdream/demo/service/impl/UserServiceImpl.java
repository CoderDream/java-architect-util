package com.coderdream.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.coderdream.demo.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@DS("mysql")
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> selectAll() {
        return  jdbcTemplate.queryForList("select * from user");
    }

    @Override
    @DS("oracle")
    public List<Map<String, Object>> selectByCondition() {
        return  jdbcTemplate.queryForList("select * from usertest"); //  where age >10
    }
}

