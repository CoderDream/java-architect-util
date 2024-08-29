package com.coderdream.demos.web.service.impl;


import com.coderdream.demos.web.dao.JobInfoDao;
import com.coderdream.demos.web.pojo.JobInfoEntity;
import com.coderdream.demos.web.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName JobInfoServiceImpl
 * @Description TODO
 * @Author lisonglin
 * @Date 2021/5/5 20:43
 * @Version 1.0
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfoEntity jobInfoEntity) {
        JobInfoEntity param =new JobInfoEntity();
        param.setUrl(jobInfoEntity.getUrl());
        param.setTime(jobInfoEntity.getTime());
        List<JobInfoEntity> jobInfoList = this.findJobInfo(param);
        if (jobInfoList.size() == 0){
            this.jobInfoDao.saveAndFlush(jobInfoEntity);
        }
    }

    @Override
    public List<JobInfoEntity> findJobInfo(JobInfoEntity jobInfoEntity) {
        Example<JobInfoEntity> jobInfoEntityExample = Example.of(jobInfoEntity);
        List<JobInfoEntity> jobInfoDaoAll = this.jobInfoDao.findAll(jobInfoEntityExample);
        return jobInfoDaoAll;
    }

    public List<JobInfoEntity> findJobTechnology(){
        return this.jobInfoDao.findJobTechnology();
    }
}
