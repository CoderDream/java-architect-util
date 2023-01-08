package com.demo.merchant.domain.repository;

import com.demo.merchant.domain.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends JpaRepository<Kind, Long>, JpaSpecificationExecutor<Kind> {

}
