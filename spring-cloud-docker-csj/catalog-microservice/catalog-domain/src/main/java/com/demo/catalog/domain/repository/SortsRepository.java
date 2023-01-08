package com.demo.catalog.domain.repository;


import com.demo.catalog.domain.entity.Sorts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SortsRepository extends JpaRepository<Sorts, Long>, JpaSpecificationExecutor<Sorts> {
    Page<Sorts> findByNameLike(@Param("name") String name, Pageable pageRequest);

    @Query("select t from Sorts t where t.name like :name and t.created >= :created")
    Page<Sorts> findByNameAndCreated(@Param("name") String name, @Param("created") Date created, Pageable pageRequest);

    Sorts findByName(@Param("name") String name);

    @Query("select s from Sorts s " +
            "left join s.subsortses b " +
            "where b.id= :id")
    Sorts findBySubsortsId(@Param("id") Long id);
}
