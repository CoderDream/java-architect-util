package com.coderdream.easyexcelpractise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.easyexcelpractise.entity.ObjectAttrItemIndexEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ObjectAttrItemIndexMapper extends BaseMapper<ObjectAttrItemIndexEntity> {


    int insertOrUpdateBatch(List<ObjectAttrItemIndexEntity> entities);
}
