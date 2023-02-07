package com.coderdream.easyexcelpractise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.easyexcelpractise.entity.ObjectBStringEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BStringMapper extends BaseMapper<ObjectBStringEntity> {

    public void insertBatch(List<ObjectBStringEntity> entities);

    public int insertOrUpdateBatch(List<ObjectBStringEntity> entities);
}
