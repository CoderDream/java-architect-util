package com.demo.merchant.domain.repository;

import com.demo.merchant.domain.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {

}
