package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.BossJobLogMapper;
import com.coderdream.freeapps.model.BossJobLogEntity;
import com.coderdream.freeapps.service.BossJobLogService;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.CdListUtils;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_boss_job_log(t_boss_job_log)】的数据库操作Service实现
 * @createDate 2023-06-08 08:53:30
 */
@Service
@Slf4j
public class BossJobLogServiceImpl extends ServiceImpl<BossJobLogMapper, BossJobLogEntity>
    implements BossJobLogService {

    @Resource
    private BossJobLogMapper bossJobLogMapper;

    @Override
    public int insertOrUpdateBatch(List<BossJobLogEntity> bossJobLogEntityList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(bossJobLogEntityList)) {
            log.info("本次批量执行的记录条数: " + bossJobLogEntityList.size());
            // 分批处理
            List<List<BossJobLogEntity>> lists = CdListUtils.splitTo(bossJobLogEntityList,
                CdConstants.BATCH_UPDATE_ROWS); //  CdConstants.BATCH_UPDATE_ROWS
            for (List<BossJobLogEntity> list : lists) {
                count += bossJobLogMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }

    static String MIDDLE_DOT = "·";
    static String MIDDLE_MINUS = "-";
    static Integer month = 12;

    @Override
    public int updateColumns() {
        QueryWrapper<BossJobLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("job_experience_max");
        List<BossJobLogEntity> bossJobLogEntityList = bossJobLogMapper.selectList(null);

        if (CollectionUtils.isNotEmpty(bossJobLogEntityList)) {
            String salaryDesc;//10-15K·16薪
            String jobExperience;//5-10年 10年以上
            String brandScaleName;//255-499人

            for (BossJobLogEntity bossJobLogEntity : bossJobLogEntityList) {
                setValues(bossJobLogEntity);
            }
        }

        return this.insertOrUpdateBatch(bossJobLogEntityList);
    }

    public static void setValues(BossJobLogEntity bossJobLogEntity) {
        String jobExperience;
        String salaryDesc;
        int avg;
        Integer max;
        String brandScaleName;
        Integer min;
        salaryDesc = bossJobLogEntity.getSalaryDesc();
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
                    bossJobLogEntity.setSalaryMin(min * month);
                    bossJobLogEntity.setSalaryMax(max * month);
                    avg = (max + min) * month / 2;
                    bossJobLogEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/时")) {
                    max = Integer.parseInt(arr[1].replace("元/时", ""));
                    bossJobLogEntity.setSalaryMin(min * 8 * 12 * 22 / 1000);
                    bossJobLogEntity.setSalaryMax(max * 8 * 12 * 22 / 1000);
                    avg = (max + min) *  8 * 12 * 22 / 2000;
                    bossJobLogEntity.setSalaryAvg(avg);
                } else if (arr[1].contains("元/天")) {
                    max = Integer.parseInt(arr[1].replace("元/天", ""));
                    bossJobLogEntity.setSalaryMin(min * 12 * 22 / 1000);
                    bossJobLogEntity.setSalaryMax(max * 12 * 22 / 1000);
                    avg = (max + min) * 12 * 22 / 2000;
                    bossJobLogEntity.setSalaryAvg(avg);
                }
            }
        }

        jobExperience = bossJobLogEntity.getJobExperience();
        if (StrUtil.isNotEmpty(jobExperience)) {
            if (jobExperience.contains(MIDDLE_MINUS)) {
                String[] arr = jobExperience.split(MIDDLE_MINUS);
                bossJobLogEntity.setJobExperienceMax(Integer.parseInt(arr[1].replace("年", "")));
            }

            if (jobExperience.contains("年以上")) {
                bossJobLogEntity.setJobExperienceMax(Integer.parseInt(jobExperience.replace("年以上", "")));
            }
        }
        brandScaleName = bossJobLogEntity.getBrandScaleName();
        if (StrUtil.isNotEmpty(brandScaleName)) {
            if (brandScaleName.contains(MIDDLE_MINUS)) {
                String[] arr = brandScaleName.split(MIDDLE_MINUS);
                bossJobLogEntity.setBrandScale(Integer.parseInt(arr[1].replace("人", "")));
            }
        }
    }
}




