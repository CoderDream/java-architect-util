package com.coderdream.passbook.dao;

import com.coderdream.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <h1>Merchants Dao 接口</h1>
 * Created by Qinyi.
 */
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    /**
     * <h2>根据 id 获取商户对象</h2>
     * @param id 商户 id
     * @return {@link Merchants}
     * */
    //Optional<Merchants> findById(Integer id);
    Optional<Merchants> findById(Integer id);

    /**
     * <h2>根据商户名称获取商户对象</h2>
     * @param name 商户名称
     * @return {@link Merchants}
     * */
    Merchants findByName(String name);
}
