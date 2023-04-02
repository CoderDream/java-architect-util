package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.DescriptionMapper;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.service.DescriptionService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_description】的数据库操作Service实现
 * @createDate 2023-03-11 10:37:56
 */
@Service
public class DescriptionServiceImpl extends ServiceImpl<DescriptionMapper, Description> implements DescriptionService {

    @Resource
    private DescriptionMapper descriptionMapper;

    @Override
    public int insertOrUpdateBatch(List<Description> descriptionList) {
        this.list();

        return descriptionMapper.insertOrUpdateBatch(descriptionList);
    }
}




