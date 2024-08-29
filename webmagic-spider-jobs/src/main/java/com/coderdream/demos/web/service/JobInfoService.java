package com.coderdream.demos.web.service;



import com.coderdream.demos.web.pojo.JobInfoEntity;
import java.util.List;

/**
 * @ClassName JobInfoService
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/5 20:42
 * @Version 1.0
 */
public interface JobInfoService {

    /**
     * 保存数据
     *
     * @param jobInfoEntity
     */
    public void save(JobInfoEntity jobInfoEntity);

    /**
     * 根据条件查询数据
     *
     * @param jobInfoEntity
     * @return
     */
    public List<JobInfoEntity> findJobInfo(JobInfoEntity jobInfoEntity);

    public List<JobInfoEntity> findJobTechnology();
}
