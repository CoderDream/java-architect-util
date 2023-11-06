package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.JobInfoMapper;
import com.coderdream.freeapps.model.JobInfoEntity;
import com.coderdream.freeapps.service.JobInfoService;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.CdListUtils;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author CoderDream
* @description 针对表【t_job_info(job_info)】的数据库操作Service实现
* @createDate 2023-06-02 13:42:50
*/
@Service
@Slf4j
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfoEntity>
    implements JobInfoService{

    @Resource
    private JobInfoMapper jobInfoMapper;

    @Override
    public int insertOrUpdateBatch(List<JobInfoEntity> jobInfoEntityList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(jobInfoEntityList)) {
            log.info("本次批量执行的记录条数: " + jobInfoEntityList.size());
            // 分批处理
            List<List<JobInfoEntity>> lists = CdListUtils.splitTo(jobInfoEntityList, CdConstants.BATCH_UPDATE_ROWS); // CdConstants.BATCH_UPDATE_ROWS
            for (List<JobInfoEntity> list : lists) {
                count += jobInfoMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }


    static String MIDDLE_DOT = "·";
    static String MIDDLE_MINUS = "-";
    static Integer month = 12;

    @Override
    public int updateColumns() {
        QueryWrapper<JobInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("job_experience_max");
        List<JobInfoEntity> jobInfoEntityList = jobInfoMapper.selectList(null);

        if (CollectionUtils.isNotEmpty(jobInfoEntityList)) {
            String salaryDesc;//10-15K·16薪
            String jobExperience;//5-10年 10年以上
            String brandScaleName;//255-499人

            for (JobInfoEntity jobInfoEntity : jobInfoEntityList) {
                setValues(jobInfoEntity);
            }
        }

        return this.insertOrUpdateBatch(jobInfoEntityList);
    }

    public static void setValues(JobInfoEntity jobInfoEntity) {
        String jobExperience;
        String salaryDesc;
        int avg;
        Integer max;
        String brandScaleName;
        Integer min;
        salaryDesc = jobInfoEntity.getSalaryDesc();
        if (StrUtil.isNotEmpty(salaryDesc)) {
            if (salaryDesc.contains(MIDDLE_DOT)) {
                String[] arr = salaryDesc.split(MIDDLE_DOT);
                salaryDesc = arr[0];
                month = Integer.parseInt(arr[1].replace("薪", ""));
            }

            if (salaryDesc.contains(MIDDLE_MINUS)) {
                String[] arr = salaryDesc.split(MIDDLE_MINUS);
                min = Integer.parseInt(arr[0]);
                if (arr[1].contains("K")) {
                    max = Integer.parseInt(arr[1].replace("K", ""));
                    jobInfoEntity.setSalaryMin(min * month);
                    jobInfoEntity.setSalaryMax(max * month);
                    avg = (max + min) * month / 2;
                    jobInfoEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/时")) {
                    max = Integer.parseInt(arr[1].replace("元/时", ""));
                    jobInfoEntity.setSalaryMin(min * 8 * 12 * 22 / 1000);
                    jobInfoEntity.setSalaryMax(max * 8 * 12 * 22 / 1000);
                    avg = (max + min) *  8 * 12 * 22 / 2000;
                    jobInfoEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/天")) {
                    max = Integer.parseInt(arr[1].replace("元/天", ""));
                    jobInfoEntity.setSalaryMin(min * 12 * 22 / 1000);
                    jobInfoEntity.setSalaryMax(max * 12 * 22 / 1000);
                    avg = (max + min) * 12 * 22 / 2000;
                    jobInfoEntity.setSalaryAvg(avg);
                }
            }
        }

        jobExperience = jobInfoEntity.getJobExperience();
        if (StrUtil.isNotEmpty(jobExperience)) {
            if (jobExperience.contains(MIDDLE_MINUS)) {
                String[] arr = jobExperience.split(MIDDLE_MINUS);
                jobInfoEntity.setJobExperienceMax(Integer.parseInt(arr[1].replace("年", "")));
            }

            if (jobExperience.contains("年以上")) {
                jobInfoEntity.setJobExperienceMax(Integer.parseInt(jobExperience.replace("年以上", "")));
            }
        }
        brandScaleName = jobInfoEntity.getBrandScaleName();
        if (StrUtil.isNotEmpty(brandScaleName)) {
            if (brandScaleName.contains(MIDDLE_MINUS)) {
                String[] arr = brandScaleName.split(MIDDLE_MINUS);
                jobInfoEntity.setBrandScale(Integer.parseInt(arr[1].replace("人", "")));
            }
        }
    }
}




