package com.coderdream.freeapps.struct;

import com.coderdream.freeapps.dto.FreeHistoryDTO;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.vo.FreeHistoryVO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * 转换类.
 */
@Mapper(componentModel = "spring")
public interface FreeHistoryStruct {

  FreeHistoryVO modelToVO(FreeHistory record);

  List<FreeHistoryVO> modelToVO(List<FreeHistory> records);

  FreeHistory voToModel(FreeHistoryVO record);

  List<FreeHistory> voToModel(List<FreeHistoryVO> records);

  FreeHistoryDTO modelToDTO(FreeHistory record);

  List<FreeHistoryDTO> modelToDTO(List<FreeHistory> records);

  FreeHistory dtoToModel(FreeHistoryDTO record);

  List<FreeHistory> dtoToModel(List<FreeHistoryDTO> records);
}