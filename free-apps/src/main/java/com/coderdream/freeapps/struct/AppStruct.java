package com.coderdream.freeapps.struct;

import com.coderdream.freeapps.dto.AppDTO;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.vo.AppVO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * 转换类.
 */
@Mapper(componentModel = "spring")
public interface AppStruct {

  AppVO modelToVO(App record);

  List<AppVO> modelToVO(List<App> records);

  App voToModel(AppVO record);

  List<App> voToModel(List<AppVO> records);

  AppDTO modelToDTO(App record);

  List<AppDTO> modelToDTO(List<App> records);

  App dtoToModel(AppDTO record);

  List<App> dtoToModel(List<AppDTO> records);
}