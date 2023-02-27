package com.coderdream.mybatisplusdemo.service;


import com.coderdream.mybatisplusdemo.dto.RiverGeoReqDto;
import com.coderdream.mybatisplusdemo.dto.RiverGeoRespDto;
import com.coderdream.mybatisplusdemo.entity.RiverGeoEntity;

import java.util.List;

public interface RiverGeoService {


    int insertOrUpdateBatch(List<RiverGeoEntity> entities);


    List<RiverGeoRespDto> list(RiverGeoReqDto riverGeoReqDto);
}
