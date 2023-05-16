package com.coderdream.freeapps.struct;

import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.vo.AppVO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * 转换类.
 */
@Mapper(componentModel = "spring")
public interface AppStruct {

  AppVO modelToVO(AppEntity record);

  List<AppVO> modelToVO(List<AppEntity> records);

  AppEntity voToModel(AppVO record);

  List<AppEntity> voToModel(List<AppVO> records);

  AppDTO modelToDTO(AppEntity record);

  List<AppDTO> modelToDTO(List<AppEntity> records);

  AppEntity dtoToModel(AppDTO record);

  List<AppEntity> dtoToModel(List<AppDTO> records);
}
