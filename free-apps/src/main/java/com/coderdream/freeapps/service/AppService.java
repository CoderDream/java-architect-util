package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.vo.AppVO;

import java.util.List;

public interface AppService {

    // 获取分页列表
    IPage<AppVO> queryPage(AppQueryPageDTO dto);

    // 获取列表
    List<AppVO> queryList(AppDTO dto);

    // 获取详情
    AppVO get(Long id);

    // 新增
    Boolean add(AppDTO dto);

    // 编辑
    Boolean edit(AppDTO dto);

    // 删除
    Boolean delete(String id);

    int insertSelective(AppEntity app);

    int insertOrUpdateBatch(List<AppEntity> appList);

    List<AppEntity> selectList(AppEntity app);
    List<AppEntity> selectListBySize(String size);

    List<AppEntity> selectNoAppIconUrl();

    List<AppEntity> selectNoUsFlag();

    List<AppEntity> selectNoSnapshot();

    List<AppEntity> selectNoDescription();

    List<AppEntity> selectNoCnFlag();

    List<AppEntity> selectDeletedAppList();

    /**
     * 有效的App列表
     *
     * @param app
     * @return
     */
    List<AppEntity> selectTodoList(AppEntity app);

    IPage<AppEntity> selectPage(Page<AppEntity> page);

    int processNoUsFlag();
    
    int processNoCnFlag();
    int processWechat();

    int genDailyPpt();

    int initTopFlag();

    int updateDescriptionCn();
}
