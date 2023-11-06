package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.SubtitleMapper;
import com.coderdream.freeapps.model.SubtitleEntity;
import com.coderdream.freeapps.service.SubtitleService;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.CdListUtils;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 * @description 针对表【t_subtitle】的数据库操作Service实现
 * @createDate 2023-06-16 19:35:48
 */
@Service
@Slf4j
public class SubtitleServiceImpl extends ServiceImpl<SubtitleMapper, SubtitleEntity>
    implements SubtitleService {

    @Resource
    private SubtitleMapper subtitleMapper;

    @Override
    public int initRecord(List<SubtitleEntity> entities) {
        int count = 0;
        if (!CollectionUtils.isEmpty(entities)) {
            log.info("本次批量执行的记录条数: " + entities.size());
            // 分批处理
            List<List<SubtitleEntity>> lists = CdListUtils.splitTo(entities, CdConstants.BATCH_UPDATE_ROWS); // CdConstants.BATCH_UPDATE_ROWS
            for (List<SubtitleEntity> list : lists) {
                count += subtitleMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }
}




