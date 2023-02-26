package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
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
}