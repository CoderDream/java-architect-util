package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.FreeHistoryMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.struct.FreeHistoryStruct;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@RequiredArgsConstructor
public class FreeHistoryServiceImpl extends
    ServiceImpl<FreeHistoryMapper, FreeHistory> implements FreeHistoryService {

  private final FreeHistoryStruct freeHistoryStruct;

  @Resource
  private FreeHistoryMapper freeHistoryMapper;

  @Override
  public IPage<FreeHistoryVO> queryPage(FreeHistoryQueryPageDTO dto) {
    IPage<FreeHistoryVO> freeHistoryPage = this.lambdaQuery().page(dto)
        .convert(freeHistory -> freeHistoryStruct.modelToVO(freeHistory));
    return freeHistoryPage;
  }

  @Override
  public List<FreeHistoryVO> queryList(FreeHistoryDTO dto) {
    List<FreeHistory> freeHistoryList = this.lambdaQuery().list();
    return freeHistoryStruct.modelToVO(freeHistoryList);
  }

  @Override
  public FreeHistoryVO get(Long id) {
    return freeHistoryStruct.modelToVO(this.getById(id));
  }

  @Override
  public Boolean add(FreeHistoryDTO dto) {
    return this.save(freeHistoryStruct.dtoToModel(dto));
  }

  @Override
  public Boolean edit(FreeHistoryDTO dto) {
    return this.updateById(freeHistoryStruct.dtoToModel(dto));
  }

  @Override
  public Boolean delete(String id) {
    return this.removeById(id);
  }

  @Override
  public int insertSelective(FreeHistory freeHistory) {
    return freeHistoryMapper.insertSelective(freeHistory);
  }

  @Override
  public int insertOrUpdateBatch(List<FreeHistory> freeHistoryList) {
    return freeHistoryMapper.insertOrUpdateBatch(freeHistoryList);
  }

  @Override
  public List<FreeHistory> selectList(FreeHistory freeHistory) {

    QueryWrapper<FreeHistory> queryWrapper = new QueryWrapper<>();
    if (StrUtil.isNotEmpty(freeHistory.getAppId())) {
      queryWrapper.eq("app_id", freeHistory.getAppId());
    }
    List<FreeHistory> result = freeHistoryMapper.selectList(queryWrapper);
    return result;
  }
}
