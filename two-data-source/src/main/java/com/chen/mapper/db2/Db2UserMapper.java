package com.chen.mapper.db2;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.chen.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenouba
 */
@Repository
@DS(value = "gp")
public interface Db2UserMapper {

    /**
     * 通过ID查询User
     * @param id
     * @return
     */
    List<UserEntity> getUserbyId(Long id);

}