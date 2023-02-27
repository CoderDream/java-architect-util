package com.coderdream.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.mybatisplusdemo.entity.RiverGeoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiverGeoMapper extends BaseMapper<RiverGeoEntity> {

    int insertOrUpdateBatch(List<RiverGeoEntity> entities);

}
