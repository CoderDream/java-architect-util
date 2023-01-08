package com.demo.manage.domain.repository;

import com.demo.manage.domain.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PartRepository extends JpaRepository<Part, Long>, JpaSpecificationExecutor<Part> {
    @Query("select t from Part t where t.name like :name")
    Page<Part> findByName(@Param("name") String name, Pageable pageRequest);
}
