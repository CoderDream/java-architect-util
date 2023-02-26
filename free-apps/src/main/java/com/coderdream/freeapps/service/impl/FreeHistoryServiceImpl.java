package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.dto.FreeHistoryQueryPageDTO;
import com.coderdream.freeapps.mapper.FreeHistoryMapper;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.struct.FreeHistoryStruct;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FreeHistoryServiceImpl extends
    ServiceImpl<FreeHistoryMapper, FreeHistory> implements FreeHistoryService {

  private final FreeHistoryStruct freeHistoryStruct;

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
}