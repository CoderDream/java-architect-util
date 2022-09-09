package com.chen.mapper.db1;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.chen.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author chenouba
 */
@Repository
@DS("mysql")
public interface Db1UserMapper {

    User getUser(Integer id);
}