package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.FreeHistory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import java.util.List;

/**
* @author CoderDream
* @description 针对表【t_free_history】的数据库操作Service
* @createDate 2023-02-27 19:08:25
*/
public interface FreeHistoryService extends IService<FreeHistory> {


    // 获取分页列表
    IPage<FreeHistoryVO> queryPage(FreeHistoryQueryPageDTO dto);

    // 获取列表
    List<FreeHistoryVO> queryList(FreeHistoryDTO dto);

    // 获取详情
    FreeHistoryVO get(Long id);

    // 新增
    Boolean add(FreeHistoryDTO dto);

    // 编辑
    Boolean edit(FreeHistoryDTO dto);

    // 删除
    Boolean delete(String id);

    int insertSelective(FreeHistory freeHistory);

    int insertOrUpdateBatch(List<FreeHistory> freeHistoryList);

    List<FreeHistory> selectList(FreeHistory freeHistory);
}
