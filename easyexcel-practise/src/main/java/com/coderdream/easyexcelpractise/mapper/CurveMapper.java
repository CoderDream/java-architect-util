package com.coderdream.easyexcelpractise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.easyexcelpractise.entity.CurveEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CurveMapper extends BaseMapper<CurveEntity> {

    public void insertBatch(List<CurveEntity> entities);
}
