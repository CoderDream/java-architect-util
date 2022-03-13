package com.shanghai.dao;

import com.shanghai.bean.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lijm on 2017/11/06.
 */
public interface UserDao extends CrudRepository<User, Long> {

    public User findById(Long id);

    @Query(value = "select * from user where id in (?1)",nativeQuery = true)
    @Modifying
    @Transactional
    public List<User> findUserByIdIn(String id);

}
