package com.demo.goods.domain.repository;


import com.demo.goods.domain.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {
    Page<Goods> findByNameLike(@Param("name") String name, Pageable pageRequest);

    @Query("select t from Goods t where t.name like :name and t.created >= :created")
    Page<Goods> findByNameAndCreated(@Param("name") String name, @Param("created") Date created, Pageable pageRequest);

    Goods findByName(@Param("name") String name);
}
