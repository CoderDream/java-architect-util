package com.coderdream.demos.web.dao;


import com.coderdream.demos.web.pojo.JobInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName JobInfoDao
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/5 20:40
 * @Version 1.0
 */
public interface JobInfoDao extends JpaRepository<JobInfoEntity, Long> {

    @Query(value = "SELECT technology FROM job_info", nativeQuery = true)
    public List<JobInfoEntity> findJobTechnology();
}
