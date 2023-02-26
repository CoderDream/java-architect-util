package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import java.util.List;

public interface FreeHistoryService {

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
}