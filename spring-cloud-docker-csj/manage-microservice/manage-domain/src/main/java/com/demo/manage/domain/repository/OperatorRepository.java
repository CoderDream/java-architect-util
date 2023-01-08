package com.demo.manage.domain.repository;

import com.demo.manage.domain.entity.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operators, Long>, JpaSpecificationExecutor<Operators> {
    @Query("select t from Operators t where t.name =?1 and t.email =?2")
    Operators findByNameAndEmail(String name, String email);

    @Query("select distinct u from Operators u where u.name= :name")
    Operators findByName(@Param("name") String name);

    @Query("select distinct u from Operators u where u.id= :id")
    Operators findById(@Param("id") Long id);

    @Query("select o from Operators o " +
            "left join o.parts p " +
            "where p.id= :id")
    List<Operators> findByPartId(@Param("id") Long id);
}
