package com.demo.catalog.domain.repository;


import com.demo.catalog.domain.entity.Subsorts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SubsortsRepository extends JpaRepository<Subsorts, Long>, JpaSpecificationExecutor<Subsorts> {
    Subsorts findByName(@Param("name") String name);

    @Query("select t from Subsorts t where t.name like :name and t.created >= :created")
    Page<Subsorts> findByNameAndCreated(@Param("name") String name, @Param("created") Date created, Pageable pageRequest);
}
