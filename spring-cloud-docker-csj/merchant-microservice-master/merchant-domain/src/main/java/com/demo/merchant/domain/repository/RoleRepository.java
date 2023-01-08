package com.demo.merchant.domain.repository;


import com.demo.merchant.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    @Query("select o from Role o " +
            "left join o.resources r " +
            "where r.id = :id")
    List<Role> findByResourceId(@Param("id") Long id);
}
