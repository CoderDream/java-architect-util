package com.coderdream.easyexcelpractise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.easyexcelpractise.entity.SampleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper extends BaseMapper<SampleEntity> {

    public void insertBatch(List<SampleEntity> sampleEntities);
}
